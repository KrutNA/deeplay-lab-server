package io.deeplay.lab.spreader;

import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.data.SolverResult;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class UnitsSpreaderByCount {
    Random random;

    public UnitsSpreaderByCount() {
        random = new Random();
    }

    private List<SolverResult.SolverOurUnit> transformInfoToResult(
            List<Short> positions,
            List<String> units
    ) {
        return positions.stream()
                .map(pos -> new SolverResult.SolverOurUnit(
                        units.remove(random.nextInt(units.size())),
                        pos)
                ).toList();
    }

    public List<SolverResult.SolverLocation> spreadUnits(
            Map<SolverInput.SolverLocation, List<Short>> locationsInfo,
            List<SolverInput.SolverOurUnit> units
    ) {
        var names = units.stream()
                .map(SolverInput.SolverOurUnit::name)
                .collect(Collectors.toList());

        return locationsInfo
                .entrySet()
                .stream()
                .map(entry -> new SolverResult.SolverLocation(
                        entry.getKey().roundId(),
                        entry.getKey().locationName(),
                        transformInfoToResult(entry.getValue(), names))
                )
                .toList();
    }
}
