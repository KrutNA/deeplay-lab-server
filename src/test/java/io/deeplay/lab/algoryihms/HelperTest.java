package io.deeplay.lab.algoryihms;

import io.deeplay.lab.algorithm.Helper;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.ReduceLocation;
import io.deeplay.lab.data.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private ReduceLocation fifthLocation;
    private ReduceLocation sixthLocation;
    private ReduceLocation seventhLocation;
    private ReduceLocation eighthLocation;

    private Unit opponentUnit;
    private Unit ourUnit;

    private Map<Integer, Unit> opponentsUnit;

    @BeforeEach
    public void setUp() throws Exception {
        opponentUnit = new Unit(1, "");
        ourUnit = new Unit(0, null);
        opponentsUnit = new HashMap<>();
    }

    @Test
    public void testConverterGeneralCase() throws Exception {
        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(2, opponentUnit);


        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(3, ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(4, ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(5, ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        fifthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsFifthLocation = new HashMap<>();
        ourUnitsFifthLocation.put(3, ourUnit);
        ourUnitsFifthLocation.put(4, ourUnit);
        fifthLocation.setOurUnits(ourUnitsFifthLocation);

        sixthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsSixthLocation = new HashMap<>();
        ourUnitsSixthLocation.put(3, ourUnit);
        ourUnitsSixthLocation.put(5, ourUnit);
        sixthLocation.setOurUnits(ourUnitsSixthLocation);

        seventhLocation = firstLocation.clone();
        Map<Integer,Unit> OurUnitsSeventhLocation = new HashMap<>();
        OurUnitsSeventhLocation.put(4, ourUnit);
        OurUnitsSeventhLocation.put(5, ourUnit);
        seventhLocation.setOurUnits(OurUnitsSeventhLocation);

        eighthLocation = firstLocation.clone();
        Map<Integer,Unit> OurUnitsEighthLocation = new HashMap<>();
        OurUnitsEighthLocation.put(3, ourUnit);
        OurUnitsEighthLocation.put(4, ourUnit);
        OurUnitsEighthLocation.put(5, ourUnit);
        eighthLocation.setOurUnits(OurUnitsEighthLocation);

        List<Location> expectedLocations = Helper.converterGeneralCase(firstLocation);

     ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);
        actual.add(secondLocation);
        actual.add(thirdLocation);
        actual.add(fourthLocation);
        actual.add(fifthLocation);
        actual.add(sixthLocation);
        actual.add(seventhLocation);
        actual.add(eighthLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    //Если рассадка опонентов непоследовательная
    public void testConverterGeneralCaseNotSequentialSeating() throws Exception {
        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(3, opponentUnit);

        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(2, ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(4, ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(5, ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        fifthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsFifthLocation = new HashMap<>();
        ourUnitsFifthLocation.put(2, ourUnit);
        ourUnitsFifthLocation.put(4, ourUnit);
        fifthLocation.setOurUnits(ourUnitsFifthLocation);

        sixthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsSixthLocation = new HashMap<>();
        ourUnitsSixthLocation.put(2, ourUnit);
        ourUnitsSixthLocation.put(5, ourUnit);
        sixthLocation.setOurUnits(ourUnitsSixthLocation);

        seventhLocation = firstLocation.clone();
        Map<Integer,Unit> OurUnitsSeventhLocation = new HashMap<>();
        OurUnitsSeventhLocation.put(4, ourUnit);
        OurUnitsSeventhLocation.put(5, ourUnit);
        seventhLocation.setOurUnits(OurUnitsSeventhLocation);

        eighthLocation = firstLocation.clone();
        Map<Integer,Unit> OurUnitsEighthLocation = new HashMap<>();
        OurUnitsEighthLocation.put(2, ourUnit);
        OurUnitsEighthLocation.put(4, ourUnit);
        OurUnitsEighthLocation.put(5, ourUnit);
        eighthLocation.setOurUnits(OurUnitsEighthLocation);

        List<Location> expectedLocations = Helper.converterGeneralCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);
        actual.add(secondLocation);
        actual.add(thirdLocation);
        actual.add(fourthLocation);
        actual.add(fifthLocation);
        actual.add(sixthLocation);
        actual.add(seventhLocation);
        actual.add(eighthLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    // Если все места заняты опонентами
    public void testConverterGeneralCaseNoPlaceForSeating() throws Exception {
        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(2, opponentUnit);
        opponentsUnit.put(3, opponentUnit);
        opponentsUnit.put(4, opponentUnit);
        opponentsUnit.put(5, opponentUnit);

        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        List<Location> expectedLocations = Helper.converterGeneralCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    public void testConverterOurCase() throws Exception {
        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(2, opponentUnit);

        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(3,ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(3,ourUnit);
        ourUnitsThirdLocation.put(4,ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(3,ourUnit);
        ourUnitsFourthLocation.put(4,ourUnit);
        ourUnitsFourthLocation.put(5,ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        List<Location> expectedLocations = Helper.converterOurCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);
        actual.add(secondLocation);
        actual.add(thirdLocation);
        actual.add(fourthLocation);

        for (Location i : expectedLocations){
            System.out.println(i.getOurUnits());
        }
        System.out.println("///////////////////////////////////////");
        for (Location i : actual){
            System.out.println(i.getOurUnits());
        }

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    //Если рассадка опонентов непоследовательная
    public void testConverterOurCaseNotSequentialSeating() throws Exception {
        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(3, opponentUnit);

        firstLocation = new ReduceLocation(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(2,ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(2,ourUnit);
        ourUnitsThirdLocation.put(4,ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.clone();
        Map<Integer,Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(2,ourUnit);
        ourUnitsFourthLocation.put(4,ourUnit);
        ourUnitsFourthLocation.put(5,ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        List<Location> expectedLocations = Helper.converterOurCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);
        actual.add(secondLocation);
        actual.add(thirdLocation);
        actual.add(fourthLocation);


        for (Location i : expectedLocations){
            System.out.println(i.getOurUnits());
        }
        System.out.println("///////////////////////////////////////");
        for (Location i : actual){
            System.out.println(i.getOurUnits());
        }

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    // Если все места заняты опонентами
    public void testConverterOurCaseNoPlaceForSeating() throws Exception {
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

        for (Location i : expectedLocations){
            System.out.println(i.getOurUnits());
        }
        System.out.println("///////////////////////////////////////");
        for (Location i : actual){
            System.out.println(i.getOurUnits());
        }


        Assertions.assertEquals(expectedLocations, actual);
    }

}