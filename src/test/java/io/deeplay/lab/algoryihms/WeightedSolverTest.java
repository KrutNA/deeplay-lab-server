package io.deeplay.lab.algoryihms;

import io.deeplay.lab.algorithm.WeightedSolver;
import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.data.SolverResult;
import io.deeplay.lab.predictor.StatisticalColdPredictor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class WeightedSolverTest {
    List<SolverInput.SolverLocation> solverLocations = new ArrayList<>();
    List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation1 = new ArrayList<>();
    List<SolverInput.SolverOurUnit> OurUnits = new ArrayList<>();
    SolverInput.SolverOurUnit ourUnit = new SolverInput.SolverOurUnit("Our", 300);

    @Test
    void solveTest1() throws IOException {
        SolverInput.SolverOpponentUnit OpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil1",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil2",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        listOpponentUnitsLocation1.add(OpponentUnit1);
        listOpponentUnitsLocation1.add(OpponentUnit2);
        listOpponentUnitsLocation1.add(OpponentUnit3);
        listOpponentUnitsLocation1.add(OpponentUnit4);

        SolverInput.SolverLocation location1 = new SolverInput.SolverLocation("1",
                "location1",
                (short) 6,
                listOpponentUnitsLocation1);
        solverLocations.add(location1);

        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);

        SolverInput solverInput = new SolverInput("world_1", solverLocations, OurUnits);
        WeightedSolver weightedSolver = new WeightedSolver(new StatisticalColdPredictor());
        SolverResult expectedLocations = weightedSolver.solve(solverInput);

        List<SolverResult.SolverLocation> locations = new ArrayList<>();
        List<SolverResult.SolverOurUnit> ourUnitsLocation1 = new ArrayList<>();
        ourUnitsLocation1.add(new SolverResult.SolverOurUnit("Our", (short) 4));
        ourUnitsLocation1.add(new SolverResult.SolverOurUnit("Our", (short) 5));
        SolverResult.SolverLocation resultLocation2 = new SolverResult.SolverLocation("1",
                "location1",
                ourUnitsLocation1);
        locations.add(resultLocation2);
        SolverResult actual = new SolverResult("world_1", locations);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    void solveTest2() throws IOException {
        SolverInput.SolverOpponentUnit OpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil1",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil2",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        listOpponentUnitsLocation1.add(OpponentUnit1);
        listOpponentUnitsLocation1.add(OpponentUnit2);
        listOpponentUnitsLocation1.add(OpponentUnit3);
        listOpponentUnitsLocation1.add(OpponentUnit4);

        SolverInput.SolverLocation location1 = new SolverInput.SolverLocation("1",
                "location1", (short) 6,
                listOpponentUnitsLocation1);
        solverLocations.add(location1);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation2 = new ArrayList<>();
        listOpponentUnitsLocation2.add(OpponentUnit1);
        listOpponentUnitsLocation2.add(OpponentUnit2);
        listOpponentUnitsLocation2.add(OpponentUnit3);

        SolverInput.SolverLocation location2 = new SolverInput.SolverLocation("2",
                "location2",
                (short) 6,
                listOpponentUnitsLocation2);
        solverLocations.add(location2);

        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);

        SolverInput solverInput = new SolverInput("world_1", solverLocations, OurUnits);
        WeightedSolver weightedSolver = new WeightedSolver(new StatisticalColdPredictor());
        SolverResult expectedLocations = weightedSolver.solve(solverInput);

        List<SolverResult.SolverLocation> locations = new ArrayList<>();

        List<SolverResult.SolverOurUnit> ourUnitsLocation1 = new ArrayList<>();
        ourUnitsLocation1.add(new SolverResult.SolverOurUnit("Our", (short) 4));
        ourUnitsLocation1.add(new SolverResult.SolverOurUnit("Our", (short) 5));
        SolverResult.SolverLocation resultLocation1 = new SolverResult.SolverLocation("1",
                "location1",
                ourUnitsLocation1);
        locations.add(resultLocation1);

        List<SolverResult.SolverOurUnit> ourUnitsLocation2 = new ArrayList<>();
        ourUnitsLocation2.add(new SolverResult.SolverOurUnit("Our", (short) 3));
        ourUnitsLocation2.add(new SolverResult.SolverOurUnit("Our", (short) 4));
        ourUnitsLocation2.add(new SolverResult.SolverOurUnit("Our", (short) 5));
        SolverResult.SolverLocation resultLocation2 = new SolverResult.SolverLocation("2",
                "location2",
                ourUnitsLocation2);
        locations.add(resultLocation2);

        SolverResult actual = new SolverResult("world_1", locations);
        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
        //When List of ourUnits is empty
    void solveTest3() throws IOException {
        SolverInput.SolverOpponentUnit OpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil1",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil2",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        listOpponentUnitsLocation1.add(OpponentUnit1);
        listOpponentUnitsLocation1.add(OpponentUnit2);
        listOpponentUnitsLocation1.add(OpponentUnit3);

        SolverInput.SolverLocation location1 = new SolverInput.SolverLocation("1",
                "location1",
                (short) 6,
                listOpponentUnitsLocation1);
        solverLocations.add(location1);

        SolverInput solverInput = new SolverInput("world_1", solverLocations, OurUnits);
        WeightedSolver weightedSolver = new WeightedSolver(new StatisticalColdPredictor());
        SolverResult expectedLocations = weightedSolver.solve(solverInput);

        SolverResult actual = new SolverResult("world_1", new ArrayList<>());
        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
        // When location have no places
    void solveTest4() throws IOException {
        SolverInput.SolverOpponentUnit OpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil1",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil2",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit5 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 4,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit6 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 5,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        listOpponentUnitsLocation1.add(OpponentUnit1);
        listOpponentUnitsLocation1.add(OpponentUnit2);
        listOpponentUnitsLocation1.add(OpponentUnit3);
        listOpponentUnitsLocation1.add(OpponentUnit4);
        listOpponentUnitsLocation1.add(OpponentUnit5);
        listOpponentUnitsLocation1.add(OpponentUnit6);

        SolverInput.SolverLocation location1 = new SolverInput.SolverLocation("1",
                "location1",
                (short) 6,
                listOpponentUnitsLocation1);
        solverLocations.add(location1);

        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);

        SolverInput solverInput = new SolverInput("world_1", solverLocations, OurUnits);
        WeightedSolver weightedSolver = new WeightedSolver(new StatisticalColdPredictor());
        SolverResult expectedLocations = weightedSolver.solve(solverInput);

        SolverResult actual = new SolverResult("world_1", new ArrayList<>());
        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
        // When locations with positive predictions more than our units
        // Ниже заметка для размышления:
        // Здесь мы добавляем наших юнитов только на третью локацию, где один вражеский юнит, в целом все правильно, т.к ->
        // предполагаемый профит около 0.43, но чисто интуитивно кажется, что правильнее было бы добавить на первую ->
        // локацию (3,4,5 места) и на 3 локацию (1,2 места).
        // то есть наш алгоритм всегда будет стремиться играть толпой наших юнитов против одного вражеского (замтека для размышления)

    void solveTest5() throws IOException {
        SolverInput.SolverOpponentUnit OpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil1",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil2",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 3,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit5 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 4,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        listOpponentUnitsLocation1.add(OpponentUnit1);
        listOpponentUnitsLocation1.add(OpponentUnit2);
        listOpponentUnitsLocation1.add(OpponentUnit3);

        SolverInput.SolverLocation location1 = new SolverInput.SolverLocation("1",
                "location1",
                (short) 6,
                listOpponentUnitsLocation1);
        solverLocations.add(location1);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation2 = new ArrayList<>();
        listOpponentUnitsLocation2.add(OpponentUnit1);
        listOpponentUnitsLocation2.add(OpponentUnit2);
        listOpponentUnitsLocation2.add(OpponentUnit3);
        listOpponentUnitsLocation2.add(OpponentUnit4);

        SolverInput.SolverLocation location2 = new SolverInput.SolverLocation("2",
                "location2",
                (short) 6,
                listOpponentUnitsLocation2);
        solverLocations.add(location2);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation3 = new ArrayList<>();
        listOpponentUnitsLocation3.add(OpponentUnit1);

        SolverInput.SolverLocation location3 = new SolverInput.SolverLocation("3",
                "location3",
                (short) 6,
                listOpponentUnitsLocation3);
        solverLocations.add(location3);

        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);

        SolverInput solverInput = new SolverInput("world_1", solverLocations, OurUnits);
        WeightedSolver weightedSolver = new WeightedSolver(new StatisticalColdPredictor());
        SolverResult expectedLocations = weightedSolver.solve(solverInput);

        List<SolverResult.SolverLocation> locations = new ArrayList<>();

        List<SolverResult.SolverOurUnit> ourUnitsLocation3 = new ArrayList<>();
        ourUnitsLocation3.add(new SolverResult.SolverOurUnit("Our", (short) 1));
        ourUnitsLocation3.add(new SolverResult.SolverOurUnit("Our", (short) 2));
        ourUnitsLocation3.add(new SolverResult.SolverOurUnit("Our", (short) 3));
        ourUnitsLocation3.add(new SolverResult.SolverOurUnit("Our", (short) 4));
        SolverResult.SolverLocation resultLocation3 = new SolverResult.SolverLocation("3",
                "location3",
                ourUnitsLocation3);
        locations.add(resultLocation3);

        SolverResult actual = new SolverResult("world_1", locations);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    void solveTest6() throws IOException {
        SolverInput.SolverOpponentUnit OpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil1",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil2",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        listOpponentUnitsLocation1.add(OpponentUnit1);

        SolverInput.SolverLocation location1 = new SolverInput.SolverLocation("1",
                "location1",
                (short) 6,
                listOpponentUnitsLocation1);

        solverLocations.add(location1);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation2 = new ArrayList<>();
        listOpponentUnitsLocation2.add(OpponentUnit1);
        listOpponentUnitsLocation2.add(OpponentUnit2);

        SolverInput.SolverLocation location2 = new SolverInput.SolverLocation("2",
                "location2",
                (short) 6,
                listOpponentUnitsLocation2);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation3 = new ArrayList<>();
        listOpponentUnitsLocation3.add(OpponentUnit1);
        listOpponentUnitsLocation3.add(OpponentUnit2);
        listOpponentUnitsLocation3.add(OpponentUnit3);

        SolverInput.SolverLocation location3 = new SolverInput.SolverLocation("3",
                "location3",
                (short) 6,
                listOpponentUnitsLocation3);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation4 = new ArrayList<>();
        listOpponentUnitsLocation4.add(OpponentUnit1);
        listOpponentUnitsLocation4.add(OpponentUnit2);
        listOpponentUnitsLocation4.add(OpponentUnit3);
        listOpponentUnitsLocation4.add(OpponentUnit4);

        SolverInput.SolverLocation location4 = new SolverInput.SolverLocation("4",
                "location4",
                (short) 6,
                listOpponentUnitsLocation4);

        OurUnits.add(ourUnit);
        OurUnits.add(ourUnit);

        SolverInput solverInput = new SolverInput("world_1", solverLocations, OurUnits);
        WeightedSolver weightedSolver = new WeightedSolver(new StatisticalColdPredictor());
        SolverResult expectedLocations = weightedSolver.solve(solverInput);

        List<SolverResult.SolverLocation> locations = new ArrayList<>();
        List<SolverResult.SolverOurUnit> ourUnitsLocation1 = new ArrayList<>();
        ourUnitsLocation1.add(new SolverResult.SolverOurUnit("Our", (short) 1));
        ourUnitsLocation1.add(new SolverResult.SolverOurUnit("Our", (short) 2));
        SolverResult.SolverLocation resultLocation2 = new SolverResult.SolverLocation("1",
                "location1",
                ourUnitsLocation1);
        locations.add(resultLocation2);

        SolverResult actual = new SolverResult("world_1", locations);
        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
        // When all predictions are negative
    void solveTest7() throws IOException {
        SolverInput.SolverOpponentUnit OpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil1",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil2",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil3",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit OpponentUnit5 = new SolverInput.SolverOpponentUnit(
                "Evil4",
                100,
                (short) 4,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        listOpponentUnitsLocation1.add(OpponentUnit1);
        listOpponentUnitsLocation1.add(OpponentUnit2);

        SolverInput.SolverLocation location1 = new SolverInput.SolverLocation("1",
                "location1",
                (short) 6,
                listOpponentUnitsLocation1);

        solverLocations.add(location1);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation2 = new ArrayList<>();
        listOpponentUnitsLocation2.add(OpponentUnit1);
        listOpponentUnitsLocation2.add(OpponentUnit2);
        listOpponentUnitsLocation2.add(OpponentUnit3);

        SolverInput.SolverLocation location2 = new SolverInput.SolverLocation("2",
                "location2",
                (short) 6,
                listOpponentUnitsLocation2);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation3 = new ArrayList<>();
        listOpponentUnitsLocation3.add(OpponentUnit1);
        listOpponentUnitsLocation3.add(OpponentUnit2);
        listOpponentUnitsLocation3.add(OpponentUnit3);
        listOpponentUnitsLocation3.add(OpponentUnit4);

        SolverInput.SolverLocation location3 = new SolverInput.SolverLocation("3",
                "location3",
                (short) 6,
                listOpponentUnitsLocation3);

        List<SolverInput.SolverOpponentUnit> listOpponentUnitsLocation4 = new ArrayList<>();
        listOpponentUnitsLocation4.add(OpponentUnit1);
        listOpponentUnitsLocation4.add(OpponentUnit2);
        listOpponentUnitsLocation4.add(OpponentUnit3);
        listOpponentUnitsLocation4.add(OpponentUnit4);
        listOpponentUnitsLocation4.add(OpponentUnit5);

        SolverInput.SolverLocation location4 = new SolverInput.SolverLocation("4",
                "location4",
                (short) 6,
                listOpponentUnitsLocation4);

        OurUnits.add(ourUnit);

        SolverInput solverInput = new SolverInput("world_1", solverLocations, OurUnits);
        WeightedSolver weightedSolver = new WeightedSolver(new StatisticalColdPredictor());
        SolverResult expectedLocations = weightedSolver.solve(solverInput);

        SolverResult actual = new SolverResult("world_1", new ArrayList<>());
        Assertions.assertEquals(expectedLocations, actual);
    }
}
