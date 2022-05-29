package io.deeplay.lab.predictor;

import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.PredictedLocation;

import java.util.List;
import java.util.stream.Collectors;

public interface LocationProfitPredictor<T, R> {

    R predictOn(T info);

    default List<R> predictOnMultiple(List<T> infos) {
        return infos
                .stream()
                .map(this::predictOn)
                .collect(Collectors.toList());
    }

}
