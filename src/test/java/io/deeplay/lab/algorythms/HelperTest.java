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

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {
    private LocationReduce FirstLocation;
    private LocationReduce SecondLocation;
    private LocationReduce ThirdLocation;
    private LocationReduce FourthLocation;
    private LocationReduce FifthLocation;

    private Unit FirstOppUnit;
    private Unit SecondOppUnit;
    private Unit ThirdOppUnit;

    private Unit OurUnit;

    private Map<Integer, Unit> opponentsUnit;

    @BeforeEach
    public void setUp() throws Exception {
        FirstOppUnit = new Unit(1, "first");
        SecondOppUnit = new Unit(2, "second");
        ThirdOppUnit = new Unit(3, "third");

        OurUnit = new Unit(0, null);

        opponentsUnit = new HashMap<>();
        opponentsUnit.put(0, FirstOppUnit);
        opponentsUnit.put(1, SecondOppUnit);
        opponentsUnit.put(2, ThirdOppUnit);

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
    }

    @Test
    public void converterGeneralCase() throws Exception {

        List<Location> expectedLocations = Helper.converterGeneralCase(FirstLocation);

        ArrayList<Location> actual = new ArrayList<>();
        actual.add(FirstLocation);
        actual.add(SecondLocation);
        actual.add(ThirdLocation);
        actual.add(FourthLocation);

        Assertions.assertEquals(expectedLocations, actual);

    }


}