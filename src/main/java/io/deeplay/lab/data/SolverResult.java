package io.deeplay.lab.data;

import java.util.List;

public record SolverResult(
        String locationName,
        List<String> unitNames
) {
}
