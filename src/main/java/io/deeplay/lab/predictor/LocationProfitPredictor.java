package io.deeplay.lab.predictor;

import io.deeplay.lab.data.SolverInput;

import java.util.List;
import java.util.stream.Collectors;

public interface LocationProfitPredictor<T, R> {

    R predictOn(SolverInput.SolverLocation location, T info);

    default List<R> predictOnMultiple(SolverInput.SolverLocation location, List<T> infos) {
        return infos
                .stream()
                .map(info -> predictOn(location, info))
                .collect(Collectors.toList());
    }

}
