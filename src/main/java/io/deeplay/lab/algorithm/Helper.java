package io.deeplay.lab.algorithm;

import io.deeplay.lab.data.SolverInput.SolverLocation;
import io.deeplay.lab.data.SolverInput.SolverOpponentUnit;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Helper {

    public static class Pair<T, K> {
        T left;
        K right;

        public Pair(T left, K right) {
            this.left = left;
            this.right = right;
        }

        public T getLeft() { return this.left; }
        public K getRight() { return this.right; }

        public static <T, K> Pair<T, K> of(T left, K right) {
            return new Pair<>(left, right);
        }

        public static <T, K> Collector<Pair<T, K>, ?, Map<T, K>> toMap() {
            return Collectors.toMap(Pair::getLeft, Pair::getRight);
        }
    }

    public static List<List<Short>> findPossibleCases(SolverLocation location) {
        var enemyPositions = location.opponentUnits()
                .stream()
                .map(SolverOpponentUnit::locatePosition)
                .toList();

        var availablePositions = IntStream.range(0, location.maxPositionsQuantity())
                .mapToObj(pos -> (short) pos)
                .filter(pos -> !enemyPositions.contains(pos))
                .toList();

        return IntStream.range(1, availablePositions.size() + 1)
                .mapToObj(count -> availablePositions.stream().limit(count).toList())
                .toList();
    }
}
