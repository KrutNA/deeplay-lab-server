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
    private LocationReduce FirstLocation;
    private LocationReduce SecondLocation;
    private LocationReduce ThirdLocation;
    private LocationReduce FourthLocation;

    private Unit OpponentUnit;

    private Unit OurUnit;

    private Map<Integer, Unit> opponentsUnit;

    @BeforeEach
    public void setUp() throws Exception {
        OpponentUnit = new Unit(1, "first");
        OurUnit = new Unit(0, null);
        opponentsUnit = new HashMap<>();
    }

    @Test
    public void converterGeneralCase() throws Exception {
        opponentsUnit.put(0, OpponentUnit);
        opponentsUnit.put(1, OpponentUnit);
        opponentsUnit.put(2, OpponentUnit);

        FirstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        SecondLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsSecondLocation = new HashMap<>();
        OurUnitsSecondLocation.put(3,OurUnit);
        SecondLocation.setOurUnits(OurUnitsSecondLocation);

        ThirdLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsThirdLocation = new HashMap<>();
        OurUnitsThirdLocation.put(3,OurUnit);
        OurUnitsThirdLocation.put(4,OurUnit);
        ThirdLocation.setOurUnits(OurUnitsThirdLocation);

        FourthLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsFourthLocation = new HashMap<>();
        OurUnitsFourthLocation.put(3,OurUnit);
        OurUnitsFourthLocation.put(4,OurUnit);
        OurUnitsFourthLocation.put(5,OurUnit);
        FourthLocation.setOurUnits(OurUnitsFourthLocation);

        List<Location> expectedLocations = Helper.converterGeneralCase(FirstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(FirstLocation);
        actual.add(SecondLocation);
        actual.add(ThirdLocation);
        actual.add(FourthLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    ///Если рассадка опонентов непоследовательная
    public void converterGeneralCaseNotSequentialSeating() throws Exception {
        opponentsUnit.put(0, OpponentUnit);
        //opponentsUnit.put(1, OpponentUnit);
        opponentsUnit.put(3, OpponentUnit);

        FirstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        SecondLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsSecondLocation = new HashMap<>();
        OurUnitsSecondLocation.put(2,OurUnit);
        SecondLocation.setOurUnits(OurUnitsSecondLocation);

        ThirdLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsThirdLocation = new HashMap<>();
        OurUnitsThirdLocation.put(2,OurUnit);
        OurUnitsThirdLocation.put(4,OurUnit);
        ThirdLocation.setOurUnits(OurUnitsThirdLocation);

        FourthLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsFourthLocation = new HashMap<>();
        OurUnitsFourthLocation.put(2,OurUnit);
        OurUnitsFourthLocation.put(4,OurUnit);
        OurUnitsFourthLocation.put(5,OurUnit);
        FourthLocation.setOurUnits(OurUnitsFourthLocation);

        List<Location> expectedLocations = Helper.converterGeneralCase(FirstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(FirstLocation);
        actual.add(SecondLocation);
        actual.add(ThirdLocation);
        actual.add(FourthLocation);

        for(Location location1 : expectedLocations){
            System.out.println(location1.getOurUnits());
        }
        System.out.println("///////////////////////////////");
        for(Location location1 : actual){
            System.out.println(location1.getOurUnits());
        }

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    /// Если все места заняты опонентами
    public void converterGeneralCaseNoPlaceForSeating() throws Exception {
        opponentsUnit.put(0, OpponentUnit);
        opponentsUnit.put(1, OpponentUnit);
        opponentsUnit.put(2, OpponentUnit);
        opponentsUnit.put(3, OpponentUnit);
        opponentsUnit.put(4, OpponentUnit);
        opponentsUnit.put(5, OpponentUnit);

        FirstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        List<Location> expectedLocations = Helper.converterGeneralCase(FirstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(FirstLocation);

        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    public void converterOurCase() throws Exception {
        opponentsUnit.put(0, OpponentUnit);
        opponentsUnit.put(1, OpponentUnit);
        opponentsUnit.put(2, OpponentUnit);

        FirstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        SecondLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsSecondLocation = new HashMap<>();
        OurUnitsSecondLocation.put(3,OurUnit);
        OurUnitsSecondLocation.put(4,OurUnit);
        OurUnitsSecondLocation.put(5,OurUnit);
        SecondLocation.setOurUnits(OurUnitsSecondLocation);

        List<Location> expectedLocations = Helper.converterOurCase(FirstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(FirstLocation);
        actual.add(SecondLocation);


        Assertions.assertEquals(expectedLocations, actual);
    }

    @Test
    ///Если рассадка опонентов непоследовательная
    public void converterOurCaseNotSequentialSeating() throws Exception {
        opponentsUnit.put(0, OpponentUnit);
        opponentsUnit.put(1, OpponentUnit);
        opponentsUnit.put(3, OpponentUnit);

        FirstLocation = new LocationReduce(1, (short) 6, opponentsUnit);

        SecondLocation = FirstLocation.copy();
        Map<Integer,Unit> OurUnitsSecondLocation = new HashMap<>();
        OurUnitsSecondLocation.put(2,OurUnit);
        OurUnitsSecondLocation.put(4,OurUnit);
        OurUnitsSecondLocation.put(5,OurUnit);
        SecondLocation.setOurUnits(OurUnitsSecondLocation);

        List<Location> expectedLocations = Helper.converterOurCase(FirstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(FirstLocation);
        actual.add(SecondLocation);

        for(Location location1 : expectedLocations){
            System.out.println(location1.getOurUnits());
        }
        System.out.println("///////////////////////////////");
        for(Location location1 : actual){
            System.out.println(location1.getOurUnits());
        }

        Assertions.assertEquals(expectedLocations, actual);
    }

}