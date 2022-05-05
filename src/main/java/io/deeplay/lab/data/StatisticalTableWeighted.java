package io.deeplay.lab.data;

import java.util.Map;

public record StatisticalTableWeighted(
        Map<Integer, Map<String, Map<Integer, Double>>> tableWeighted
) {
}
