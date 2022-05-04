package io.deeplay.lab;

import io.deeplay.lab.data.Round;
import io.deeplay.lab.db.Database;
import io.deeplay.lab.parser.HistoricalDataParser;
import io.deeplay.lab.parser.RoundFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static Database createSqlDatabase() {
        return null;
    }

    public static InputStream createInputStream() {
        return null;
    }
    public static void main(String[] args) throws IOException {
        var parser = new HistoricalDataParser();
        var filter = new RoundFilter();

        try (Database db = createSqlDatabase();
             InputStream is = createInputStream() {
            List<Round> rounds = new ArrayList<>();
            final var count = new AtomicInteger();
            parser.process(is)
                    .takeWhile(Objects::nonNull)
                    .filter(filter)
                    .forEachOrdered((round) -> {
                        if (count.get() < db.capacity) {
                            count.incrementAndGet();
                            rounds.add(round);
                        } else {
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
