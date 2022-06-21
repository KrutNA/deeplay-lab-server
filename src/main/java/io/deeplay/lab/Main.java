package io.deeplay.lab;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.algorithm.WeightedSolver;
import io.deeplay.lab.data.Round;
import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.db.Database;
import io.deeplay.lab.db.DatabaseCredentials;
import io.deeplay.lab.db.SqlDatabase;
import io.deeplay.lab.parser.HistoricalDataParser;
import io.deeplay.lab.parser.RoundFilter;
import io.deeplay.lab.predictor.StatisticalColdPredictor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Main {

    private static Database createSqlDatabase() throws IOException {
        try (var credsData = Main.class.getClassLoader().getResourceAsStream("db_creds.json")) {
            var creds = new ObjectMapper().readValue(credsData, DatabaseCredentials.class);
            return new SqlDatabase(creds.url(), creds.user(), creds.password());
        } catch (IOException e) {
            throw new IOException("Couldn't read database credentials file.", e);
        }
    }

    public static RoundFilter createRoundFilter() {
        List<Predicate<Round>> filters = List.of(
                RoundFilter::checkRequiredFields,
                RoundFilter::checkContainsUnits,
                RoundFilter::checkPossiblePositions,
                RoundFilter::checkTotalSum
        );
        return new RoundFilter(filters);
    }

    static final String usage = """
                    Expected execution type as parameters.
                    Possible types:
                    --db-update {json}
                    --solve {input_json} {output_json}""";

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println(usage);
            return;
        }
        if (args[0].equals("--db-update") && args.length == 2) {
            processDatabaseUpdate(args[1]);
        } else if (args[0].equals("--solve") && args.length == 3) {
            processSolve(args[1], args[2]);
        } else {
            System.out.println(usage);
        }
    }

    private static void processDatabaseUpdate(String inputFile) throws IOException {
        var parser = new HistoricalDataParser();
        var filter = createRoundFilter();

        try (Database db = createSqlDatabase();
             InputStream is = new FileInputStream(inputFile)) {
            List<Round> rounds = new ArrayList<>();
            final var count = new AtomicInteger();
            parser.process(is)
                    .takeWhile(Objects::nonNull)
                    .filter(filter)
                    .forEachOrdered((round) -> {
                        rounds.add(round);
                        if (count.getAndIncrement() >= db.CHUNK_SIZE) {
                            db.insertRounds(rounds);
                            count.set(0);
                            rounds.clear();
                        }
                    });
            if (rounds.isEmpty() == false) {
                db.insertRounds(rounds);
            }
        }
    }

    private static void processSolve(String inputFile, String outputFile) throws IOException {
        var mapper = new ObjectMapper();
        var input = mapper.readValue(new File(inputFile), SolverInput.class);
        var predictor = new StatisticalColdPredictor();
        var solver = new WeightedSolver(predictor);
        var output = solver.solve(input);
        mapper.writeValue(new File(outputFile), output);
    }
}
