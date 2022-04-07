import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import java.io.*;

public class HistoryParser {
    private final int batchSize;
    private final ObjectMapper mapper;
    private final JsonParser parser;
    public boolean streamEndFlag = false;

    public List<Round> read() throws Exception {
        var rounds = new ArrayList<Round>();

        for (int i = 0; i < batchSize; i++) {
            if (parser.nextToken() != JsonToken.END_ARRAY) {
                rounds.add(mapper.readValue(parser, Round.class));

            } else {
                streamEndFlag = true;
                break;
            }
        }

        return rounds;
    }

    HistoryParser(InputStream is, int batch_size) throws IOException {
        this.batchSize = batch_size;

        this.mapper = new ObjectMapper();
        this.parser = mapper.getFactory().createParser(is);

        if (parser.nextToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Stream has already been read");
        }
    }
}

record Round(
        UUID roundId,
        String locationName,
        int locationLevel,
        int maxPositionsQuantity,
        List<Unit> opponentUnits,
        List<Unit> ourUnits
) {}

record Unit(
        String name,
        float sourceGoldCount,
        float goldProfit,
        int locatePosition,
        int evasiveness,
        int aggression,
        int responseAggression,
        int shield
) {}