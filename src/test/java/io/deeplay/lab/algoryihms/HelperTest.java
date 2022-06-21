package io.deeplay.lab.algoryihms;

import io.deeplay.lab.algorithm.Helper;
import io.deeplay.lab.data.SolverInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class HelperTest {

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
}