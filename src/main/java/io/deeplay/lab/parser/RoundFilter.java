package io.deeplay.lab.parser;

import io.deeplay.lab.data.Round;
import io.deeplay.lab.data.Unit;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RoundFilter implements Predicate<Round> {
    List<Predicate<Round>> checkers;

    public RoundFilter(List<Predicate<Round>> checkers) {
        this.checkers = checkers;
    }

    @Override
    public boolean test(Round round) {
        for (var checker : checkers) {
            if (checker.test(round) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkRequiredFields(Round round) {
        return Objects.nonNull(round.roundId())
                && Objects.nonNull(round.locationName());
    }

    public static boolean checkContainsUnits(Round round) {
        return round.ourUnits().isEmpty()
                && round.opponentUnits().isEmpty();
    }

    public static boolean checkPossiblePositions(Round round) {
        return extractUnits(round)
                .allMatch(unit -> unit.locatePosition() <= round.maxPositionsQuantity());
    }

    public static  boolean checkTotalSum(Round round) {
        final double epsilon = 1e-3;
        return extractUnits(round)
                .mapToDouble(Unit::goldProfit)
                .sum()
                < epsilon;
    }

    private static Stream<Unit> extractUnits(Round round) {
        return Stream.concat(
                round.ourUnits().stream(),
                round.opponentUnits().stream()
        );
    }

}
