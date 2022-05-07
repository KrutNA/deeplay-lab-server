package io.deeplay.lab.predictor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.PredictedLocation;
import io.deeplay.lab.data.StatisticalTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class StatisticalColdPredictor implements LocationProfitPredictor {
    public final String statisticsTablePath = "src/main/resources/statistical_table.json";
    private StatisticalTable table;

    StatisticalColdPredictor() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream(statisticsTablePath);
        var parser = mapper.getFactory().createParser(is);

        this.table = mapper.readValue(parser, StatisticalTable.class);

    }

    @Override
    public PredictedLocation predictLocation(Location location) {
        double sum = 0;
        for (var pos : location.getOurUnits().keySet()) {
            sum += table.table().get(location.getUnitCount()).get(pos);
        }
        return new PredictedLocation(location.getId(), sum);
    }

    @Override
    public List<PredictedLocation> predictLocations(List<Location> locations) {
        List<PredictedLocation> predictions = new ArrayList<>(locations.size());

        for (var loc : locations) {
            double sum = 0;
            for (var pos : loc.getOurUnits().keySet()) {
                sum += table.table().get(loc.getUnitCount()).get(pos);
            }

            predictions.add(new PredictedLocation(loc.getId(), sum));
        }

        return predictions;
    }
}
