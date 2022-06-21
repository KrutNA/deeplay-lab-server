package io.deeplay.lab.predictor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.data.SolverInput;
import io.deeplay.lab.data.StatisticalTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class StatisticalColdPredictor implements LocationProfitPredictor<List<Short>, Double> {
    public final String statisticsTablePath = "src/main/resources/statistical_table.json";
    private StatisticalTable table;

    public StatisticalColdPredictor() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream(statisticsTablePath);
        var parser = mapper.getFactory().createParser(is);

        this.table = mapper.readValue(parser, StatisticalTable.class);

    }

    @Override
    public Double predictOn(SolverInput.SolverLocation location, List<Short> info) {
        double sum = 0.0;
        var tableElement = table.table()
                .get((short) (location.opponentUnits().size() + info.size()));
        for (var pos : info) {
            sum += tableElement.get(pos);
        }
        return sum;
    }
}
