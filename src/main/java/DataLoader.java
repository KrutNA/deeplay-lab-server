import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class DataLoader {

    public void resetTables(Connection conn) throws SQLException {
        //ATTENTION!!! THAT WILL DROP ALL DATA
        String sql_drop =
                        "drop table if exists Unit;" +
                        "drop table if exists Location;" +
                        "drop table if exists Round;" +
                        "drop table if exists HistoricalIteration;";
        PreparedStatement statement_drop = conn.prepareStatement(sql_drop);
        statement_drop.execute();

        String sql_create =
                        "create table Unit (id bigserial primary key, name varchar(30));" +
                        "create table Location (id serial primary key, name varchar(30));" +
                        "create table Round (id bigserial primary key, round_id uuid, location_id int, location_level int, location_size smallint, dt_insert timestamp);" +
                        "create table HistoricalIteration (id bigserial primary key, round_id bigint, unit_id bigint, unit_team smallint, profit float, position smallint, evasiveness smallint, aggression smallint, response_aggression smallint, shield smallint);";

        PreparedStatement statement_create = conn.prepareStatement(sql_create);
        statement_create.execute();
    }

    public static void loadHistoryAll(HistoryParser parser, Connection conn) throws Exception {

        while (!parser.streamEndFlag) {
            var rounds = parser.read();

            loadRoundsBatch(rounds, conn);
        }

    }

    public static void loadHistoryBatch(HistoryParser parser, Connection conn) throws Exception {
        if (!parser.streamEndFlag) {
            var rounds = parser.read();

            loadRoundsBatch(rounds, conn);
        }
    }

    protected static void loadRoundsBatch(List<Round> rounds, Connection conn) throws SQLException {

        var allUnits = new ArrayList<Unit>();
        var allLocations = new ArrayList<Location>();
        for (var round: rounds) {
            allUnits.addAll(round.ourUnits());
            allUnits.addAll(round.opponentUnits());

            allLocations.add(new Location(round.locationName(), round.maxPositionsQuantity()));
        }

        updateUnits(allUnits, conn);
        updateLocations(allLocations, conn);
        insertRound(rounds, conn);
        insertHistoricalIteration(rounds, conn);
    }

    private static void updateUnits(List<Unit> units, Connection conn) throws SQLException {
        String sql_create = "create temporary table unit_batch (name varchar(30));";

        PreparedStatement statm_create = conn.prepareStatement(sql_create);
        statm_create.execute();

        String sql = "insert into unit_batch (name) values (?);";

        PreparedStatement statm = conn.prepareStatement(sql);

        conn.setAutoCommit(false);

        for (var unit: units) {
            statm.setString(1, unit.name());
            statm.addBatch();
        }

        statm.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);

        String sql_insert = "" +
                "insert into Unit (name) " +
                "(select distinct ub.name from unit_batch ub left join Unit u on ub.name = u.name  where u.name is null);" +
                "drop table if exists unit_batch;";

        statm = conn.prepareStatement(sql_insert);
        statm.execute();
    }

    private static void updateLocations(List<Location> locations, Connection conn) throws SQLException {
        String sql_create = "create temporary table location_batch (name varchar(30));";

        PreparedStatement statm_create = conn.prepareStatement(sql_create);
        statm_create.execute();

        String sql = "insert into location_batch (name) values (?);";

        PreparedStatement statm = conn.prepareStatement(sql);

        conn.setAutoCommit(false);

        for (var location: locations) {
            statm.setString(1, location.name());

            statm.addBatch();
        }

        statm.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);

        String sql_insert = "" +
                "insert into Location (name) " +
                "(select distinct lb.name from location_batch lb left join Location l on lb.name = l.name  where l.name is null);" +
                "drop table if exists location_batch;";

        statm = conn.prepareStatement(sql_insert);
        statm.execute();
    }

    private static void insertRound(List<Round> rounds, Connection conn) throws SQLException {
        String sql_prepare = "create temporary table round_batch (round_id uuid, loc_name varchar(30), location_level int, location_size smallint, dt_insert timestamp);";

        PreparedStatement statm_create = conn.prepareStatement(sql_prepare);
        statm_create.execute();

        String sql_temp = "insert into round_batch (round_id, loc_name, location_level, location_size, dt_insert) values (?, ?, ?, ?, ?);";
        PreparedStatement statm = conn.prepareStatement(sql_temp);

        conn.setAutoCommit(false);

        for (var round: rounds) {
            statm.setObject(1, round.roundId());
            statm.setString(2, round.locationName());
            statm.setInt(3, round.locationLevel());
            statm.setInt(4, round.maxPositionsQuantity());

            Timestamp insertDate = new Timestamp(System.currentTimeMillis());
            statm.setTimestamp(5, insertDate);

            statm.addBatch();
        }

        statm.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);

        String sql_insert = "" +
                "insert into Round (round_id, location_id, location_level, location_size, dt_insert)" +
                "(select rb.round_id, l.id, rb.location_level, rb.location_size, rb.dt_insert as location_id from round_batch rb inner join Location l on rb.loc_name = l.name);" +
                "drop table if exists round_batch;";

        statm = conn.prepareStatement(sql_insert);
        statm.execute();
    }

    private static void insertHistoricalIteration(List<Round> rounds, Connection conn) throws SQLException {

        String sql_tmp = "create temporary table HistoricalIteration_batch (round_id uuid, unit_name varchar(30), unit_team smallint, profit float, position smallint, evasiveness smallint, aggression smallint, response_aggression smallint, shield smallint);";

        PreparedStatement statm_create = conn.prepareStatement(sql_tmp);
        statm_create.execute();

        String sql_temp = "insert into HistoricalIteration_batch (round_id, unit_name, unit_team, profit, position, evasiveness, aggression, response_aggression, shield) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statm = conn.prepareStatement(sql_temp);

        conn.setAutoCommit(false);

        for (var round: rounds) {
            List<List<Unit>> units = new ArrayList<>();
            units.add(round.opponentUnits());
            units.add(round.ourUnits());

            int i = 0;
            for (var unitList: units) {
                for (var unit: unitList) {
                    statm.setObject(1, round.roundId());
                    statm.setString(2, unit.name());
                    statm.setInt(3, i);
                    statm.setFloat(4, unit.goldProfit());
                    statm.setInt(5, unit.locatePosition());
                    statm.setInt(6, unit.evasiveness());
                    statm.setInt(7, unit.aggression());
                    statm.setInt(8, unit.responseAggression());
                    statm.setInt(9, unit.shield());

                    statm.addBatch();
                }

                i += 1;
            }
        }

        statm.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);

        String sql_insert = "" +
                "insert into HistoricalIteration (round_id, unit_id, unit_team, profit, position, evasiveness, aggression, response_aggression, shield)" +
                "(select r.id as round_id, u.id as unit_id, hib.unit_team, hib.profit, hib.position, hib.evasiveness, hib.aggression, hib.response_aggression, hib.shield from HistoricalIteration_batch hib inner join Unit u on hib.unit_name = u.name inner join Round r on r.round_id = hib.round_id);" +
                "drop table if exists HistoricalIteration_batch;";

        statm = conn.prepareStatement(sql_insert);
        statm.execute();
    }
}

record Location(
        String name,
        int maxPositionsQuantity
) {}