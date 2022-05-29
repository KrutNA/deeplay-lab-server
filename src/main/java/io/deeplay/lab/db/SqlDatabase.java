package io.deeplay.lab.db;

import io.deeplay.lab.data.Round;
import io.deeplay.lab.data.Team;
import io.deeplay.lab.data.UnitHistory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlDatabase implements Database {
  Connection connection;
  Logger logger;

  private static Connection initializeConnection(String url, String user, String pass) {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      // TODO: log error on no postgres class
      throw new RuntimeException(e);
    }

    try {
      return DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public SqlDatabase(String url, String user, String pass) {
    connection = initializeConnection(url, user, pass);
    logger = Logger.getLogger(SqlDatabase.class.getName());
    logger.log(Level.INFO, "Connection established.");
  }

  private Optional<Long> processQueryByStatementWithResultId(
          final String sql,
          PrepareStatementProcessor processor
  ) {
    try (var st = connection.prepareStatement(sql)) {
      processor.process(st);

      try (var rs = st.executeQuery()) {
        if (rs.next()) {
          return Optional.of(rs.getLong("id"));
        }

        return Optional.empty();
      }
    } catch (SQLException e) {
      // TODO: log and process on sql exception
      logger.log(Level.SEVERE, e.toString());
      try {
        if (connection.isClosed()) {
          throw new RuntimeException();
        }
      } catch (SQLException ignored) {
      }
    }

    return Optional.empty();
  }

  private void processQueryByStatement(
          final String sql,
          PrepareStatementProcessor processor
  ) {
    try (var st = connection.prepareStatement(sql)) {
      processor.process(st);

      st.executeLargeUpdate();

    } catch (SQLException e) {
      // TODO: log and process on sql exception
      logger.log(Level.SEVERE, e.toString());
      e.printStackTrace();
      try {
        if (connection.isClosed()) {
          throw new RuntimeException();
        }
      } catch (SQLException ignored) {
      }
    }
  }

  private Optional<Long> processInsertLocationQuery(final String location) {
    final var sql = """
            INSERT INTO locations (name)
            VALUES (?)
            ON CONFLICT DO NOTHING
            RETURNING id""";
    return processQueryByStatementWithResultId(sql, (st) -> st.setString(1, location));
  }

  private Optional<Long> processSelectLocationQuery(final String location) {
    final var sql = """
            SELECT id
            FROM locations
            WHERE name = ?""";
    return processQueryByStatementWithResultId(sql, (st) -> st.setString(1, location));
  }

  private Optional<Long> processInsertRoundQuery(Round round, long locationId) {
    final var sql = """
            INSERT INTO rounds (round_id, location_id, location_level, location_size)
            VALUES (?, ?, ?, ?)
            ON CONFLICT DO NOTHING
            RETURNING id""";
    PrepareStatementProcessor processor = (st) -> {
      st.setObject(1, round.roundId());
      st.setLong(2, locationId);
      st.setShort(3, round.locationLevel());
      st.setShort(4, round.maxPositionsQuantity());
    };

    return processQueryByStatementWithResultId(sql, processor);
  }

  private String createSqlWithFieldsBySize(
          final String header,
          final String base,
          final String sep,
          final String footer,
          final int size
  ) {
    var sql = new StringBuilder(header);

    final var valuesJoiner = new StringJoiner(sep);
    for (var i = 0; i < size; ++i) {
      valuesJoiner.add(base);
    }

    sql.append(valuesJoiner);
    sql.append(footer);

    return sql.toString();
  }

  @Override
  public Optional<Long> getOrInsertLocationAsId(final String location) {
    return processInsertLocationQuery(location)
            .or(() -> processSelectLocationQuery(location));

  }

  @Override
  public Optional<Long> insertRoundAsId(final Round round) {
    return getOrInsertLocationAsId(round.locationName())
            .flatMap((locationId) -> processInsertRoundQuery(round, locationId));
  }

  @Override
  public void insertUnitsIfNotExist(final Round round) {
    final var header = """
            INSERT INTO units (name, team)
            VALUES\040
            """;
    final var base = "    (?, ?)";
    final var sep = ",\n";
    final var footer = "\nON CONFLICT DO NOTHING";
    final var size = round.ourUnits().size() + round.opponentUnits().size();
    final var sql = createSqlWithFieldsBySize(header, base, sep, footer, size);

    PrepareStatementProcessor process = (st) -> {
      final var i = new AtomicInteger();

      UnitsTeamProcessor processUnits = (units, team) -> {
        for (var unit : units) {
          st.setString(i.incrementAndGet(), unit.name());
          st.setShort(i.incrementAndGet(), team.toInner());
        }
      };

      processUnits.process(round.ourUnits(), Team.Our);
      processUnits.process(round.opponentUnits(), Team.Opponent);
    };

    processQueryByStatement(sql, process);
  }

  @Override
  public void insertHistoricalIterations(final long round_id, final Round round) {
    final var header = """
            WITH data (
                unit_name, profit, position,
                evasiveness, aggression, response_aggression, shield
            ) AS (VALUES
            """;
    final var base = "    (?, ?, ?, ?, ?, ?, ?)";
    final var sep = ",\n";
    final var footer = """
            \n)
            INSERT INTO historical_iterations (
                round_id, unit_id, profit, position,
                evasiveness, aggression, response_aggression, shield
            )
            SELECT ?, units.id, profit, position,
                   evasiveness, aggression, response_aggression, shield
                FROM data
                LEFT JOIN units
                ON data.unit_name = units.name
            ON CONFLICT DO NOTHING""";
    final var size = round.ourUnits().size() + round.opponentUnits().size();
    final var sql = createSqlWithFieldsBySize(header, base, sep, footer, size);

    PrepareStatementProcessor process = (st) -> {
      final var i = new AtomicInteger();

      UnitsProcessor processUnits = (units) -> {
        for (var unit : units) {
          st.setString(i.incrementAndGet(), unit.name());
          st.setFloat(i.incrementAndGet(), unit.goldProfit());
          st.setShort(i.incrementAndGet(), unit.locatePosition());
          st.setShort(i.incrementAndGet(), unit.evasiveness());
          st.setShort(i.incrementAndGet(), unit.aggression());
          st.setShort(i.incrementAndGet(), unit.responseAggression());
          st.setShort(i.incrementAndGet(), unit.shield());
        }
      };

      processUnits.process(round.ourUnits());
      processUnits.process(round.opponentUnits());
      st.setLong(i.incrementAndGet(), round_id);
    };

    processQueryByStatement(sql, process);
  }

  @Override
  public void insertRound(Round round) {
    var roundId = insertRoundAsId(round);
    if (roundId.isEmpty()) return;

    insertUnitsIfNotExist(round);
    insertHistoricalIterations(roundId.get(), round);
  }

  @Override
  public void insertLocationsFromRounds(List<Round> rounds) {
    var header = """
              INSERT INTO locations (name)
              VALUES
              """;
    var base = "    (?)";
    var sep = ",\n";
    var footer = "\nON CONFLICT DO NOTHING";

    var locations = rounds
            .stream()
            .map(Round::locationName)
            .distinct()
            .toList();

    var sql = createSqlWithFieldsBySize(header, base, sep, footer, locations.size());

    PrepareStatementProcessor processor = (st) -> {
      var i = 0;
      for (var location : locations) {
        st.setString(++i, location);
      }
    };

    processQueryByStatement(sql, processor);
  }

  @Override
  public void insertRoundsFromRounds(List<Round> rounds) {
    var header = """
            WITH data (
                round_id, location_name, location_level, location_size
            ) AS (VALUES
            """;
    var base = "    (?, ?, ?, ?)";
    var sep = ",\n";
    var footer = """
            \n)
            INSERT INTO rounds (round_id, location_id, location_level, location_size)
            SELECT round_id, locations.id, location_level, location_size
                FROM data
                LEFT JOIN locations
                    ON data.location_name = locations.name
            ON CONFLICT DO NOTHING""";

    var sql = createSqlWithFieldsBySize(header, base, sep, footer, rounds.size());

    PrepareStatementProcessor processor = (st) -> {
      var i = 0;
      for (var round : rounds) {
        st.setObject(++i, round.roundId());
        st.setString(++i, round.locationName());
        st.setShort(++i, round.locationLevel());
        st.setShort(++i, round.maxPositionsQuantity());
      }
    };

    processQueryByStatement(sql, processor);
  }

  @Override
  public void insertUnitsFromRounds(List<Round> rounds) {
    final var header = """
              INSERT INTO units (name, team)
              VALUES
              """;
    final var base = "    (?, ?)";
    final var sep = ",\n";
    final var footer = "\nON CONFLICT DO NOTHING";

    Function<Function<Round, List<UnitHistory>>, List<UnitHistory>> extractUnits = (function) -> rounds.stream()
          .flatMap(round -> function.apply(round).stream())
          .distinct()
          .toList();

    final var ourUnits = extractUnits.apply(Round::ourUnits);
    final var oppUnits = extractUnits.apply(Round::opponentUnits);

    final var size = ourUnits.size() + oppUnits.size();
    final var sql = createSqlWithFieldsBySize(header, base, sep, footer, size);

    final var i = new AtomicInteger();
    PrepareStatementProcessor processor = (st) -> {
      SqlBiConsumer<List<UnitHistory>, Team> processUnits = (units, team) -> {
        final var teamId = team.toInner();
        for (final var unit : units) {
          st.setString(i.incrementAndGet(), unit.name());
          st.setShort(i.incrementAndGet(), teamId);
        }
      };

      processUnits.accept(ourUnits, Team.Our);
      processUnits.accept(oppUnits, Team.Opponent);
    };

    processQueryByStatement(sql, processor);
  }

  @Override
  public void insertHistoricalIterationsFromRounds(List<Round> rounds) {
    final var header = """
            WITH data (
                round_uuid, unit_name, profit, position,
                evasiveness, aggression, response_aggression, shield
            ) AS (VALUES
            """;
    final var base = "    (?, ?, ?, ?, ?, ?, ?, ?)";
    final var sep = ",\n";
    final var footer = """
            \n)
            INSERT INTO historical_iterations (
                round_id, unit_id, profit, position,
                evasiveness, aggression, response_aggression, shield
            )
            SELECT rounds.id, units.id, profit, position,
                   evasiveness, aggression, response_aggression, shield
                FROM data
                LEFT JOIN units
                    ON data.unit_name = units.name
                LEFT JOIN rounds
                    ON data.round_uuid = rounds.round_id
            ON CONFLICT DO NOTHING""";

    final List<Function<Round, List<UnitHistory>>> extractUnits
            = List.of(Round::ourUnits, Round::opponentUnits);
    final var it = new AtomicInteger();

    Supplier<Integer> calcSize = () -> rounds.stream()
            .mapToInt(round -> extractUnits
                    .get(it.get())
                    .apply(round)
                    .size())
            .sum();

    PrepareStatementProcessor process = (st) -> {
      final var i = new AtomicInteger();
      for (final var round : rounds) {
        var units = extractUnits.get(it.get()).apply(round);

        for (final var unit : units) {
          st.setObject(i.incrementAndGet(), round.roundId());
          st.setString(i.incrementAndGet(), unit.name());
          st.setFloat(i.incrementAndGet(), unit.goldProfit());
          st.setShort(i.incrementAndGet(), unit.locatePosition());
          st.setShort(i.incrementAndGet(), unit.evasiveness());
          st.setShort(i.incrementAndGet(), unit.aggression());
          st.setShort(i.incrementAndGet(), unit.responseAggression());
          st.setShort(i.incrementAndGet(), unit.shield());
        }
      }
    };

    extractUnits.forEach((func) -> {
      final var size = calcSize.get();
      final var sql = createSqlWithFieldsBySize(header, base, sep, footer, size);
      processQueryByStatement(sql, process);
      it.incrementAndGet();
    });
  }

  @Override
  public void insertRounds(List<Round> rounds) {
    insertLocationsFromRounds(rounds);
    insertUnitsFromRounds(rounds);
    insertRoundsFromRounds(rounds);
    insertHistoricalIterationsFromRounds(rounds);
  }

  @Override
  public void close() throws IOException {
    try {
      connection.close();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
  }

  @FunctionalInterface
  interface PrepareStatementProcessor {
    void process(PreparedStatement st) throws SQLException;
  }

  @FunctionalInterface
  interface UnitsTeamProcessor {
    void process(List<UnitHistory> unit, Team team) throws SQLException;
  }
  @FunctionalInterface
  interface UnitsProcessor {
    void process(List<UnitHistory> unit) throws SQLException;
  }

  @FunctionalInterface
  interface SqlBiConsumer<T, U> {
    void accept(T t, U u) throws SQLException;
  }
}
