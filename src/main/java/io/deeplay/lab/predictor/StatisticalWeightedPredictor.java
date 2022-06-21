package io.deeplay.lab.predictor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.data.StatisticalWeightedTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StatisticalWeightedPredictor implements LocationProfitPredictor<List<Short>, Double> {
    public final String statisticsWeightedTablePath = "src/main/resources/statistical_weighted_table.json";
    private StatisticalWeightedTable tableWeighted;

    public StatisticalWeightedPredictor() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream(statisticsWeightedTablePath);
        var parser = mapper.getFactory().createParser(is);

        this.tableWeighted = mapper.readValue(parser, StatisticalWeightedTable.class);
        System.out.println(tableWeighted.tableWeighted());
    }

    @Override
    public Double predictOn(SolverInput.SolverLocation location, List<Short> info) {

        return null;
    }

}
