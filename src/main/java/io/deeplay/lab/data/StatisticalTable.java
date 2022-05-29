package io.deeplay.lab.data;

import java.util.Map;

public record StatisticalTable(
        Map<Short, Map<Short, Double>> table
) {
}
