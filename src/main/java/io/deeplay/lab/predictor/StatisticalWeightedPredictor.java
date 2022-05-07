package io.deeplay.lab.predictor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.PredictedLocation;
import io.deeplay.lab.data.StatisticalWeightedTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StatisticalWeightedPredictor implements LocationProfitPredictor {
    public final String statisticsWeightedTablePath = "src/main/resources/statistical_weighted_table.json";
    private StatisticalWeightedTable tableWeighted;

    StatisticalWeightedPredictor() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream(statisticsWeightedTablePath);
        var parser = mapper.getFactory().createParser(is);

        this.tableWeighted = mapper.readValue(parser, StatisticalWeightedTable.class);
        System.out.println(tableWeighted.tableWeighted());
    }
    @Override
    public PredictedLocation predictLocation(Location location) {

        return null;
    }

    @Override
    public List<PredictedLocation> predictLocations(List<Location> locations) {
        return null;

    }

}
