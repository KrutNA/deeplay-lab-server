package io.deeplay.lab.algorythms;

import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.LocationReduce;
import io.deeplay.lab.data.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class HelperTest {
    private LocationReduce firstLocation;
    private LocationReduce secondLocation;
    private LocationReduce thirdLocation;
    private LocationReduce fourthLocation;
    private LocationReduce fifthLocation;
    private LocationReduce sixthLocation;
    private LocationReduce seventhLocation;
    private LocationReduce eighthLocation;

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

        firstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(3, ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(4, ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(5, ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        fifthLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsFifthLocation = new HashMap<>();
        ourUnitsFifthLocation.put(3, ourUnit);
        ourUnitsFifthLocation.put(4, ourUnit);
        fifthLocation.setOurUnits(ourUnitsFifthLocation);

        sixthLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsSixthLocation = new HashMap<>();
        ourUnitsSixthLocation.put(3, ourUnit);
        ourUnitsSixthLocation.put(5, ourUnit);
        sixthLocation.setOurUnits(ourUnitsSixthLocation);

        seventhLocation = firstLocation.copy();
        Map<Integer,Unit> OurUnitsSeventhLocation = new HashMap<>();
        OurUnitsSeventhLocation.put(4, ourUnit);
        OurUnitsSeventhLocation.put(5, ourUnit);
        seventhLocation.setOurUnits(OurUnitsSeventhLocation);

        eighthLocation = firstLocation.copy();
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

        firstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(2, ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(4, ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsFourthLocation = new HashMap<>();
        ourUnitsFourthLocation.put(5, ourUnit);
        fourthLocation.setOurUnits(ourUnitsFourthLocation);

        fifthLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsFifthLocation = new HashMap<>();
        ourUnitsFifthLocation.put(2, ourUnit);
        ourUnitsFifthLocation.put(4, ourUnit);
        fifthLocation.setOurUnits(ourUnitsFifthLocation);

        sixthLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsSixthLocation = new HashMap<>();
        ourUnitsSixthLocation.put(2, ourUnit);
        ourUnitsSixthLocation.put(5, ourUnit);
        sixthLocation.setOurUnits(ourUnitsSixthLocation);

        seventhLocation = firstLocation.copy();
        Map<Integer,Unit> OurUnitsSeventhLocation = new HashMap<>();
        OurUnitsSeventhLocation.put(4, ourUnit);
        OurUnitsSeventhLocation.put(5, ourUnit);
        seventhLocation.setOurUnits(OurUnitsSeventhLocation);

        eighthLocation = firstLocation.copy();
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

        firstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

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

        firstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(3,ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(3,ourUnit);
        ourUnitsThirdLocation.put(4,ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.copy();
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

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    //Если все места заняты опонентами
    public void testConverterOurCaseNotSequentialSeating() throws Exception {
        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(3, opponentUnit);

        firstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        secondLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsSecondLocation = new HashMap<>();
        ourUnitsSecondLocation.put(2,ourUnit);
        secondLocation.setOurUnits(ourUnitsSecondLocation);

        thirdLocation = firstLocation.copy();
        Map<Integer,Unit> ourUnitsThirdLocation = new HashMap<>();
        ourUnitsThirdLocation.put(2,ourUnit);
        ourUnitsThirdLocation.put(4,ourUnit);
        thirdLocation.setOurUnits(ourUnitsThirdLocation);

        fourthLocation = firstLocation.copy();
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


        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    //Если рассадка опонентов непоследовательная
    public void testConverterOurCaseNoPlaceForSeating() throws Exception {
        opponentsUnit.put(0, opponentUnit);
        opponentsUnit.put(1, opponentUnit);
        opponentsUnit.put(2, opponentUnit);
        opponentsUnit.put(3, opponentUnit);
        opponentsUnit.put(4, opponentUnit);
        opponentsUnit.put(5, opponentUnit);

        firstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        List<Location> expectedLocations = Helper.converterOurCase(firstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(firstLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

}