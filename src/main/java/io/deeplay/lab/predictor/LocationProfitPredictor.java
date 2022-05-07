package io.deeplay.lab.predictor;

import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.PredictedLocation;

import java.util.List;

public interface LocationProfitPredictor {
    public PredictedLocation predictLocation(Location location);

    public List<PredictedLocation> predictLocations(List<Location> locations);

}
