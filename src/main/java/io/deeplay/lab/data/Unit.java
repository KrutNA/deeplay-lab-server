package io.deeplay.lab.data;

public record Unit(
        String name,
        float sourceGoldCount,
        float goldProfit,
        short locatePosition,
        short evasiveness,
        short aggression,
        short responseAggression,
        short shield
) {
}
