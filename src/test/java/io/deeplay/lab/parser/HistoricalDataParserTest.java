package io.deeplay.lab.parser;

import io.deeplay.lab.data.Round;
import io.deeplay.lab.data.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


class HistoricalDataParserTest {
    private List<Round> roundExpected = new ArrayList<>();
    private List<Round> roundActual = new ArrayList<>();
    private UUID id;
    private List<Unit> opponentUnits = new ArrayList<>();
    private List<Unit> ourUnits = new ArrayList<>();

    @Test
    void name() throws Exception {
        opponentUnits.add(new Unit
                ("name1", (float) 132.8, (float) 0.0, (short) 1, (short) 0, (short) 0, (short) 0, (short) 0));
        opponentUnits.add(new Unit
                ("name2", (float) 191.6, (float) 0.0, (short) 2, (short) 0, (short) 0, (short) 0, (short) 0));
        opponentUnits.add(new Unit
                ("name3", (float) 100.0, (float) 1.5, (short) 3, (short) 1, (short) 1, (short) 0, (short) 0));
        opponentUnits.add(new Unit
                ("name4", (float) 100.0, (float) 0.0, (short) 4, (short) 0, (short) 0, (short) 0, (short) 0));


        ourUnits.add(new Unit
                ("name5", (float) 100.0, (float) -1.0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0));
        ourUnits.add(new Unit
                ("name6", (float) 115.5, (float) -0.5, (short) 5, (short) 0, (short) 0, (short) 0, (short) 0));

        id = UUID.fromString("aaa72c29-a55a-4a24-9a1c-94e8a9d24eda");

        roundExpected.add(new Round(id, "Factoria0", (short) 10, (short) 6, opponentUnits, ourUnits));

        String FilePath = "src/test/resources/Historical_test_file.json";

        try (InputStream is = new FileInputStream(FilePath)) {

            var parser = new HistoricalDataParser();
            parser.process(is)
                    .takeWhile(Objects::nonNull)
                    .forEachOrdered((round) -> {
                        roundActual.add(round);
                    });

            System.out.println(roundActual);
            System.out.println(roundExpected);
            Assertions.assertEquals(roundExpected, roundActual);


        }

    }

}
