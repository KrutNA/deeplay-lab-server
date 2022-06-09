package io.deeplay.lab.algoryihms;

import io.deeplay.lab.algorithm.Helper;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.ReduceLocation;
import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.data.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class HelperTest {
    private ReduceLocation firstLocation;
    private ReduceLocation secondLocation;
    private ReduceLocation thirdLocation;
    private ReduceLocation fourthLocation;

    private Unit opponentUnit;
    private Unit ourUnit;


    private Map<Integer, Unit> opponentsUnit;

    @Test
    public void testFindPossibleCases() {
        List<SolverInput.SolverOpponentUnit> solverOpponentUnits = new ArrayList<>();
        SolverInput.SolverOpponentUnit solverOpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 2,
                (short) 0,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);
        solverOpponentUnits.add(solverOpponentUnit1);
        solverOpponentUnits.add(solverOpponentUnit2);
        solverOpponentUnits.add(solverOpponentUnit3);
        solverOpponentUnits.add(solverOpponentUnit4);

        SolverInput.SolverLocation solverLocation1 = new SolverInput.SolverLocation("1",
                "location",
                (short) 6,
                solverOpponentUnits);


        List<Short> FirstShortList = new ArrayList<>();
        FirstShortList.add((short) 4);

        List<Short> SecondShortList = new ArrayList<>();
        SecondShortList.add((short) 4);
        SecondShortList.add((short) 5);

        List<List<Short>> actual = new ArrayList<>();
        actual.add(FirstShortList);
        actual.add(SecondShortList);

        List<List<Short>> expectedLocations = Helper.findPossibleCases(solverLocation1);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    public void testFindPossibleCasesWhenNotSequentialSeating() {
        List<SolverInput.SolverOpponentUnit> solverOpponentUnits = new ArrayList<>();
        SolverInput.SolverOpponentUnit solverOpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);
        solverOpponentUnits.add(solverOpponentUnit1);
        solverOpponentUnits.add(solverOpponentUnit2);
        solverOpponentUnits.add(solverOpponentUnit3);

        SolverInput.SolverLocation solverLocation1 = new SolverInput.SolverLocation("1",
                "location",
                (short) 6,
                solverOpponentUnits);


        List<Short> FirstShortList = new ArrayList<>();
        FirstShortList.add((short) 2);

        List<Short> SecondShortList = new ArrayList<>();
        SecondShortList.add((short) 2);
        SecondShortList.add((short) 4);

        List<Short> ThirdShortList = new ArrayList<>();
        ThirdShortList.add((short) 2);
        ThirdShortList.add((short) 4);
        ThirdShortList.add((short) 5);

        List<List<Short>> actual = new ArrayList<>();
        actual.add(FirstShortList);
        actual.add(SecondShortList);
        actual.add(ThirdShortList);

        List<List<Short>> expectedLocations = Helper.findPossibleCases(solverLocation1);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    public void testFindPossibleCasesWhenNoPlaceForSeating() {
        List<SolverInput.SolverOpponentUnit> solverOpponentUnits = new ArrayList<>();
        SolverInput.SolverOpponentUnit solverOpponentUnit1 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit2 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 1,
                (short) 1,
                (short) 0,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit3 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 2,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit4 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 3,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit5 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 4,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        SolverInput.SolverOpponentUnit solverOpponentUnit6 = new SolverInput.SolverOpponentUnit(
                "Evil",
                100,
                (short) 5,
                (short) 0,
                (short) 1,
                (short) 0,
                (short) 0);

        solverOpponentUnits.add(solverOpponentUnit1);
        solverOpponentUnits.add(solverOpponentUnit2);
        solverOpponentUnits.add(solverOpponentUnit3);
        solverOpponentUnits.add(solverOpponentUnit4);
        solverOpponentUnits.add(solverOpponentUnit5);
        solverOpponentUnits.add(solverOpponentUnit6);

        SolverInput.SolverLocation solverLocation1 = new SolverInput.SolverLocation("1",
                "location",
                (short) 6,
                solverOpponentUnits);

        List<List<Short>> actual = new ArrayList<>();

        List<List<Short>> expectedLocations = Helper.findPossibleCases(solverLocation1);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    public void testConverterOurCase() {
        opponentUnit = new Unit(1, "");
        ourUnit = new Unit(0, null);
        opponentsUnit = new HashMap<>();

        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(2, opponentUnit);

        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.clone();
        Map<Integer, Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(3, ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.clone();
        Map<Integer, Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(3, ourUnit);
        ourUnitsThirdLocation.put(4, ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.clone();
        Map<Integer, Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(3, ourUnit);
        ourUnitsFourthLocation.put(4, ourUnit);
        ourUnitsFourthLocation.put(5, ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        List<Location> expectedLocations = Helper.converterOurCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);
        actual.add(secondLocation);
        actual.add(thirdLocation);
        actual.add(fourthLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    //Если рассадка опонентов непоследовательная
    public void testConverterOurCaseWhenNotSequentialSeating() {
        opponentUnit = new Unit(1, "");
        ourUnit = new Unit(0, null);
        opponentsUnit = new HashMap<>();

        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(3, opponentUnit);

        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.clone();
        Map<Integer, Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(2, ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.clone();
        Map<Integer, Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(2, ourUnit);
        ourUnitsThirdLocation.put(4, ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.clone();
        Map<Integer, Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(2, ourUnit);
        ourUnitsFourthLocation.put(4, ourUnit);
        ourUnitsFourthLocation.put(5, ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        List<Location> expectedLocations = Helper.converterOurCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);
        actual.add(secondLocation);
        actual.add(thirdLocation);
        actual.add(fourthLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    // Если все места заняты опонентами
    public void testConverterOurCaseWhenNoPlaceForSeating() {
        opponentUnit = new Unit(1, "");
        ourUnit = new Unit(0, null);
        opponentsUnit = new HashMap<>();

        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(2, opponentUnit);
        opponentsUnit.put(3, opponentUnit);
        opponentsUnit.put(4, opponentUnit);
        opponentsUnit.put(5, opponentUnit);

        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        List<Location> expectedLocations = Helper.converterOurCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

}