package io.deeplay.lab.parser;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeplay.lab.data.Round;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class HistoricalDataParser implements Parser<Round> {
    ObjectMapper mapper;
    Logger logger;

    public HistoricalDataParser() {
        mapper = new ObjectMapper();
        logger = Logger.getLogger(HistoricalDataParser.class.getName());
    }

    @Override
    public Stream<Round> process(InputStream is) throws IOException {
        var parser = mapper.getFactory().createParser(is);

        if (parser.nextToken() != JsonToken.START_ARRAY) {
            // TODO: Process errors
            throw new IllegalStateException();
        }

        return Stream.generate(() -> {
            try {
                if (parser.nextToken() == JsonToken.END_ARRAY) {
                    is.close();
                    return null;
                }

                return mapper.readValue(parser, Round.class);
            } catch (IOException e) {
                // TODO: Process errors
                logger.log(Level.SEVERE, e.toString());
                return null;
            }
        });
    }
}
