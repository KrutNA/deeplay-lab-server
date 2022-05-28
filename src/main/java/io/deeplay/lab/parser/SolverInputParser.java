package io.deeplay.lab.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.data.SolverInput;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class SolverInputParser implements Parser<SolverInput> {
    @Override
    public Stream<SolverInput> process(InputStream is) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var solverInput = mapper.readValue(is, SolverInput.class);
        return Stream.of(solverInput);
    }
}
