package io.deeplay.lab.algorithm;

import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.data.SolverResult;
import io.deeplay.lab.predictor.LocationProfitPredictor;
import io.deeplay.lab.algorithm.Helper.Pair;
import io.deeplay.lab.spreader.UnitsSpreaderByCount;
import io.deeplay.lab.spreader.UnitsSpreaderByProfit;

import java.util.List;
import java.util.stream.Collectors;

public class WeightedSolver implements Solver {
    LocationProfitPredictor<List<Short>, Double> predictor;
    UnitsSpreaderByProfit spreaderByProfit = new UnitsSpreaderByProfit();
    UnitsSpreaderByCount spreaderByCount = new UnitsSpreaderByCount();

    WeightedSolver(LocationProfitPredictor<List<Short>, Double> predictor) {
        this.predictor = predictor;
    }

    @Override
    public SolverResult solve(SolverInput input) {
        var predictions = input.locations()
                .stream()
                .map(location -> Pair.of(
                        location,
                        Helper.findPossibleCases(location)))
                .map(pair_loc_cases -> Pair.of(
                        pair_loc_cases.getLeft(),
                        Pair.of(pair_loc_cases.getRight(),  
                                predictor.predictOnMultiple(
                                        pair_loc_cases.getLeft(),
                                        pair_loc_cases.getRight()))))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

        var spreads = spreaderByProfit.spreadUnits(predictions, input.ourUnits().size());
        var locations = spreaderByCount.spreadUnits(spreads, input.ourUnits());
        return new SolverResult(input.worldName(), locations);
    }

}
