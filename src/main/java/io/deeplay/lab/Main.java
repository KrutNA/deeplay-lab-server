package io.deeplay.lab;

import io.deeplay.lab.data.Round;
import io.deeplay.lab.db.Database;
import io.deeplay.lab.parser.HistoricalDataParser;
import io.deeplay.lab.parser.RoundFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Main {

    public static Database createSqlDatabase() {
        return null;
    }

    public static InputStream createInputStream() {
        return null;
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

    public static void main(String[] args) throws IOException {
        var parser = new HistoricalDataParser();
        var filter = createRoundFilter();

        try (Database db = createSqlDatabase();
             InputStream is = createInputStream()) {
            List<Round> rounds = new ArrayList<>();
            final var count = new AtomicInteger();
            parser.process(is)
                    .takeWhile(Objects::nonNull)
                    .filter(filter)
                    .forEachOrdered((round) -> {
                        rounds.add(round);
                        if (count.getAndIncrement() >= db.chunkSize) {
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
}
