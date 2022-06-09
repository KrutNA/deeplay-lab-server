package io.deeplay.lab.parser;

import io.deeplay.lab.data.Round;
import io.deeplay.lab.data.Unit;
import io.deeplay.lab.data.UnitHistory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RoundFilter implements Predicate<Round> {
    List<Predicate<Round>> checkers;

    public RoundFilter(List<Predicate<Round>> checkers) {

        this.checkers = checkers;
    }

    @Override
    public boolean test(Round round) {
        return checkers.stream()
                .allMatch((checker) -> checker.test(round));
    }

    public static boolean checkRequiredFields(Round round) {
        return round.roundId()       != null
            && round.locationName()  != null
            && round.ourUnits()      != null
            && round.opponentUnits() != null;
    }

    public static boolean checkContainsUnits(Round round) {
        return !(round.ourUnits().isEmpty()
              && round.opponentUnits().isEmpty());
    }

    public static boolean checkPossiblePositions(Round round) {
        if (round.maxPositionsQuantity() < 0) return false;

        var uniquePositionsCount = new AtomicInteger();
        var positions = extractUnits(round)
                .mapToInt(UnitHistory::locatePosition)
                .distinct()
                .peek((pos) -> uniquePositionsCount.incrementAndGet());

        var unitsCount = round.ourUnits().size() + round.opponentUnits().size();
        var maxPosQty = round.maxPositionsQuantity();
        return positions.allMatch(pos -> pos < maxPosQty && pos >= 0)
                && uniquePositionsCount.get() == unitsCount;
    }

    public static  boolean checkTotalSum(Round round) {
        final double epsilon = 1e-3;
        var totalSum = extractUnits(round)
                .mapToDouble(UnitHistory::goldProfit)
                .sum();
        return Math.abs(totalSum) < epsilon;
    }

    private static Stream<UnitHistory> extractUnits(Round round) {
        return Stream.concat(
                round.ourUnits().stream(),
                round.opponentUnits().stream()
        );
    }

}
