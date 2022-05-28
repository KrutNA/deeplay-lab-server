package io.deeplay.lab.data;

import java.util.List;

public record SolverInput(
        String worldName,
        List<SolverLocation> locations,
        List<SolverOurUnit> ourUnits
) {
    public record SolverLocation(
            String roundId,
            String locationName,
            short maxPositionsQuantity,
            List<SolverOpponentUnit> opponentUnits
    ) {
    }

    public record SolverOpponentUnit(
            String name,
            float sourceGoldCount,
            short locatePosition,
            short shield,
            short evasiveness,
            short aggression,
            short responseAggression
    ) {
    }

    public record SolverOurUnit(
            String name,
            float sourceGoldCount
    ) {
    }
}
