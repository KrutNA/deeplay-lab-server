package io.deeplay.lab.predictors;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.PredictedLocation;
import io.deeplay.lab.data.StatisticalTable;
import io.deeplay.lab.data.StatisticalTableWeighted;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PredictionStatisticalWeighted implements LocationProfitPredictor {
    public final String statisticsWeightedTablePath = "src/main/resources/statistical_weighted_table.json";
    private StatisticalTableWeighted tableWeighted;

    PredictionStatisticalWeighted() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream(statisticsWeightedTablePath);
        var parser = mapper.getFactory().createParser(is);

        this.tableWeighted = mapper.readValue(parser, StatisticalTableWeighted.class);
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
