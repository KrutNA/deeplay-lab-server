package io.deeplay.lab.spreader;

import io.deeplay.lab.data.SolverInput.SolverLocation;
import io.deeplay.lab.data.SolverResult;
import io.deeplay.lab.data.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UnitsSpreaderByCount {
    Random random;

    public UnitsSpreaderByCount() {
        random = new Random();
    }

    private SolverResult transformInfoToResult(
            SolverLocation location,
            int count,
            List<Unit> units
    ) {
        List<String> names = new ArrayList<>(count);

        while (count-- != 0) {
            var idx = random.nextInt(units.size());
            var unit = units.remove(idx);
            names.add(unit.name());
        }

        return new SolverResult(
                location.locationName(),
                names
        );
    }

    public List<SolverResult> spreadUnits(
            Map<SolverLocation, Integer> locationsInfo,
            List<Unit> units
    ) {
        return  locationsInfo
                .entrySet()
                .stream()
                .map(entry -> transformInfoToResult(
                            entry.getKey(),
                            entry.getValue(),
                            units
                    )
                )
                .toList();
    }
}
