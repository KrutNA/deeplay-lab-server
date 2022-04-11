package io.deeplay.lab;

import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;

public class ObservationParser {

    public static void parse(InputStream is) throws Exception {
        var mapper = new ObjectMapper();
        var parser = mapper.getFactory().createParser(is);

        if (parser.nextToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("");
        }

        while (parser.nextToken() != JsonToken.END_ARRAY) {
            mapper.readValue(parser, Round.class);
        }
    }
}

record Round(
        String roundId,
        String locationName,
        int locationLevel,
        int maxPositionsQuantity,
        List<Unit> opponentUnits,
        List<Unit> ourUnits
) {
}

record Unit(
        String name,
        float sourceGoldCount
) {
}