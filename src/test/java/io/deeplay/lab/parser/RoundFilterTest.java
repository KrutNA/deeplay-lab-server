package io.deeplay.lab.parser;

import io.deeplay.lab.data.Round;
import io.deeplay.lab.data.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoundFilterTest {
    private Round round;
    private UUID id;
    Unit ourUnit;
    Unit opponentUnit;
    private List<Unit> opponentUnits = new ArrayList<>();
    private List<Unit> ourUnits = new ArrayList<>();

    @Test
    public void checkRequiredFields() throws Exception {
        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);
        assertEquals(true,RoundFilter.checkRequiredFields(round));
    }

    @Test
    //UUID IsNull
    public void checkRequiredFieldsWhenUUIDIsNull() throws Exception {
        Round round = new Round(null,"",(short)1,(short) 6,opponentUnits,ourUnits);
        assertEquals(false,RoundFilter.checkRequiredFields(round));
    }

    @Test
    //LocationName Is Null
    public void checkRequiredFieldsWhenLocationNameIsNull() throws Exception {
        Round round = new Round(UUID.randomUUID(),null,(short)1,(short) 6,opponentUnits,ourUnits);
        assertEquals(false,RoundFilter.checkRequiredFields(round));
    }

    @Test
    //LocationName and UUID Are Null
    public void checkRequiredFieldsWhenLocationNameAndUUIDAreNull() throws Exception {
        Round round = new Round(null,null,(short)1,(short) 6,opponentUnits,ourUnits);
        assertEquals(false,RoundFilter.checkRequiredFields(round));
    }

    @Test
    // opponentUnits and ourUnits are Null
    public void checkContainsUnitsWhenOppAndOurUnitsAreNull() throws Exception {
        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(false,RoundFilter.checkContainsUnits(round));
    }

    @Test
    public void checkContainsUnits() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 0.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) 0.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(true,RoundFilter.checkContainsUnits(round));
    }

    @Test
    // OpponentUnits isEmpty
    public void checkContainsUnitsWhenOpponentUnitsIsEmpty() throws Exception {
        opponentUnit = new Unit("",(float)0.0,(float) 0.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(true,RoundFilter.checkContainsUnits(round));
    }

    @Test
    // OurUnits isEmpty
    public void checkContainsUnitsWhenOurUnitsIsEmpty() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 0.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(true,RoundFilter.checkContainsUnits(round));
    }

    @Test
    // OurUnits and opponents are empty
    public void checkContainsUnitsWhenOurUnitsAndOpponentsAreEmpty() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 0.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(true,RoundFilter.checkContainsUnits(round));
    }

    @Test
    public void checkPossiblePositions() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 0.0,(short) 5,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) 0.0,(short) 2,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(true,RoundFilter.checkPossiblePositions(round));
    }

    @Test
    // Unit locate position more than location position max
    public void checkPossiblePositionsWhenUnitPositionMoreThanLocPosition() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 0.0,(short) 6,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) 0.0,(short) 5,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(false,RoundFilter.checkPossiblePositions(round));
    }

    @Test
    // Unit locate position takes negative meaning
    public void checkPossiblePositionsWhenUnitPositionTakesNegativeMeaning() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 0.0,(short) -1,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) 0.0,(short) -1,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(false,RoundFilter.checkPossiblePositions(round));
    }


    @Test
    public void checkTotalSum() throws Exception {
        ourUnit = new Unit("",(float)10.0,(float) 5.0,(short) 1,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)10.0,(float) -5.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(true,RoundFilter.checkTotalSum(round));
    }

    @Test
    //Not Zero Sum
    public void checkTotalSumNotZeroSumFirst() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 1.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) -5.0,(short) 1,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(false,RoundFilter.checkTotalSum(round));
    }

    @Test
    //Not Zero Sum
    public void checkTotalSumNotZeroSumSecond() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) 5.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) 5.0,(short) 1,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(false,RoundFilter.checkTotalSum(round));
    }

    @Test
    //Not Zero Sum
    public void checkTotalSumNotZeroSumThird() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) -5.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) 3.0,(short) 1,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(false,RoundFilter.checkTotalSum(round));
    }

    @Test
    //Not Zero Sum
    public void checkTotalSumNotZeroSumFourth() throws Exception {
        ourUnit = new Unit("",(float)0.0,(float) -5.0,(short) 0,(short) 0,(short) 0,(short) 0,(short) 0);
        opponentUnit = new Unit("",(float)0.0,(float) -3.0,(short) 1,(short) 0,(short) 0,(short) 0,(short) 0);

        opponentUnits.add(opponentUnit);
        ourUnits.add(ourUnit);

        Round round = new Round(UUID.randomUUID(),"",(short)1,(short) 6,opponentUnits,ourUnits);

        assertEquals(false,RoundFilter.checkTotalSum(round));
    }
}