package io.deeplay.lab.parser;

import io.deeplay.lab.data.SolverInput.SolverOurUnit;
import io.deeplay.lab.parser.SolverInputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.postgresql.util.ReaderInputStream;

import java.io.StringReader;

public class SolverInputParserTest {

    public String getInput() {
        return """
                {
                "worldName": "FunkyLand",
                "locations": [
                {
                    "roundId": "7943952547646014580",
                    "locationName": "Factoria15",
                    "maxPositionsQuantity": 6,
                    "opponentUnits": [
                    {
                        "name": "Иван Факов",
                        "sourceGoldCount": 86.25,
                        "locatePosition": 2,
                        "shield": 0,
                        "evasiveness": 0,
                        "aggression": 0,
                        "responseAggression": 1
                    }
                    ]
                }
                ],
                "ourUnits": [
                {
                    "name": "РИНО КОТОНИЧ",
                    "sourceGoldCount": 294.25
                },
                {
                    "name": "ЭТЕЛЬСКА АДАЛЬРИСИЧ",
                    "sourceGoldCount": 204.5
                },
                {
                    "name": "МЕРСТЕН БРОНДРЕАКА",
                    "sourceGoldCount": 119.75
                }
                ]
                }""";
    }

    @Test
    public void test() throws Exception {
        var input = getInput();
        var is = new ReaderInputStream(new StringReader(input));
        var parser = new SolverInputParser();
        var solverInputList = parser.process(is).toList();

        Assertions.assertEquals(solverInputList.size(), 1);
        var solverInput = solverInputList.get(0);
        Assertions.assertEquals(solverInput.worldName(), "FunkyLand");

        Assertions.assertEquals(solverInput.locations().size(), 1);
        var location = solverInput.locations().get(0);
        Assertions.assertEquals(location.roundId(), "7943952547646014580");
        Assertions.assertEquals(location.locationName(), "Factoria15");
        Assertions.assertEquals(location.maxPositionsQuantity(), 6);

        Assertions.assertEquals(location.opponentUnits().size(), 1);
        var opponent = location.opponentUnits().get(0);
        Assertions.assertEquals(opponent.name(), "Иван Факов");
        Assertions.assertEquals(opponent.sourceGoldCount(), 86.25);
        Assertions.assertEquals(opponent.locatePosition(), 2);
        Assertions.assertEquals(opponent.shield(), 0);
        Assertions.assertEquals(opponent.evasiveness(), 0);
        Assertions.assertEquals(opponent.aggression(), 0);
        Assertions.assertEquals(opponent.responseAggression(), 1);


        Assertions.assertEquals(solverInput.ourUnits().size(), 3);
        var our = solverInput.ourUnits();
        Assertions.assertEquals(our.get(0), new SolverOurUnit("РИНО КОТОНИЧ", 294.25f));
        Assertions.assertEquals(our.get(1), new SolverOurUnit("ЭТЕЛЬСКА АДАЛЬРИСИЧ", 204.5f));
        Assertions.assertEquals(our.get(2), new SolverOurUnit("МЕРСТЕН БРОНДРЕАКА", 119.75f));
    }
}
