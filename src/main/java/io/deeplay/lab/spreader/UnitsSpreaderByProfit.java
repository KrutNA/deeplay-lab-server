package io.deeplay.lab.spreader;

import io.deeplay.lab.algorithm.Solver;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.algorithm.Helper.Pair;
import io.deeplay.lab.data.SolverInput.SolverLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitsSpreaderByProfit {
    private static class MutableDouble {
        double value;

        public MutableDouble(double value) {
            this.value = value;
        }

        public MutableDouble addAndGet(double value) {
            return new MutableDouble(this.value += value);
        }

        public void sub(double value) {
            this.value -= value;
        }
    }

    private static class TotalProfit {
        SolverLocation location;
        List<MutableDouble> profits;

        public TotalProfit(Map.Entry<SolverLocation, Pair<List<List<Short>>, List<Double>>> prediction) {
            location = prediction.getKey();
            var sum = new MutableDouble(0.0);
            profits = prediction.getValue().getRight().stream()
                    .map(sum::addAndGet)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    private record ReduceResult(
            SolverLocation location,
            int unitsCount
    ) {
    }


    private List<TotalProfit> convertPredictionsToTotalProfits(
            Map<SolverLocation, Pair<List<List<Short>>, List<Double>>> predictions
    ) {
        return predictions
                .entrySet()
                .stream()
                .parallel()
                .map(TotalProfit::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private void reduceTotalProfits(
            List<TotalProfit> totals,
            TotalProfit maxProfitTotal,
            double maxProfit,
            int unitsCount
    ) {
        if (maxProfitTotal.profits.size() == unitsCount) {
            totals.remove(maxProfitTotal);
        } else {
            var max = 0.0;
            for (var i = 0; i < unitsCount; ++i) {
                max = maxProfitTotal.profits.remove(0).value;
            }
            var maxFinal = max;
            maxProfitTotal.profits.forEach(v -> v.sub(maxFinal));
        }
    }


    private ReduceResult reduceMostValuableTotalProfit(
            List<TotalProfit> totalProfits,
            int unitsCount
    ) {
        var maxProfit = Double.NEGATIVE_INFINITY;
        var maxProfitTotal = totalProfits.get(0);
        var maxProfitUnitsCount = 0;


        for (var totalProfit : totalProfits) {
            var profits = totalProfit.profits;

            for (var i = 0; i < profits.size() && i < unitsCount; ++i) {
                var profit = profits.get(i).value / (double) (i + 1);

                if (profit > maxProfit) {
                    maxProfit = profit;
                    maxProfitTotal = totalProfit;
                    maxProfitUnitsCount = i + 1;
                }
            }
        }

        if (maxProfit < 0.0) return null;

        reduceTotalProfits(totalProfits, maxProfitTotal, maxProfit, maxProfitUnitsCount);

        return new ReduceResult(maxProfitTotal.location, maxProfitUnitsCount);
    }


    public Map<SolverLocation, List<Short>> spreadUnits(
            Map<SolverLocation, Pair<List<List<Short>>, List<Double>>> predictions,
            int unitsCount
    ) {
        var totalProfits = convertPredictionsToTotalProfits(predictions);

        Map<SolverLocation, Integer> unitCountsByLocation = new HashMap<>();

        while (totalProfits.isEmpty() == false) {
            var result = reduceMostValuableTotalProfit(totalProfits, unitsCount);
            if (result == null) break;
            unitsCount -= unitCountsByLocation
                    .compute(result.location(),
                            (k, v) -> (v != null ? v : 0) + result.unitsCount());
        }

        return unitCountsByLocation
                .entrySet()
                .parallelStream()
                .map(entry -> Pair.of(
                        entry.getKey(),
                        predictions.get(entry.getKey())
                                .getLeft()
                                .get(entry.getValue() - 1)))
                .collect(Pair.toMap());
    }
}
