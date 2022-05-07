package io.deeplay.lab.predictor;

import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.PredictedLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PredictorRandom implements LocationProfitPredictor {
    final Random random = new Random();

    @Override
    public List<PredictedLocation> predictLocations(List<Location> locations) {
        List<PredictedLocation> predictions = new ArrayList<>(locations.size());

        for (var loc : locations) {
            predictions.add(new PredictedLocation(loc.getId(), randomPrediction()));

        }

        return predictions;
    }

    @Override
    public PredictedLocation predictLocation(Location location) {
        return new PredictedLocation(location.getId(), randomPrediction());

    }

    private double randomPrediction() {

        return random.nextGaussian(0, 2);
    }

}

