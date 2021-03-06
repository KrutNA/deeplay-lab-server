package io.deeplay.lab.data;

import java.util.List;
import java.util.UUID;

public record Round(
        UUID roundId,
        String locationName,
        short locationLevel,
        short maxPositionsQuantity,
        List<UnitHistory> opponentUnits,
        List<UnitHistory> ourUnits
) {
}

