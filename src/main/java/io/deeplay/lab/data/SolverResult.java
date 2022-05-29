package io.deeplay.lab.data;

import java.util.List;

public record SolverResult(
        String worldName,
        List<SolverLocation> locations
) {
    public record SolverLocation (
            String roundId,
            String locationName,
            List<SolverOurUnit> ourUnits
    ) {
    }

    public record SolverOurUnit (
            String name,
            short locatePosition
    ) {
    }
}
