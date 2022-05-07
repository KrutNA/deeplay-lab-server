package io.deeplay.lab.algorithm;

import com.google.common.collect.Sets;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.Unit;

import java.util.*;

public class Helper {
    public static List<Location> converterGeneralCase(Location location) {
        // NEED TO BE TESTED
        List<Location> locationConverted = new ArrayList<>();

        Set<Integer> positions = new HashSet<>(location.getFreePositions());
        List<Set<Integer>> combinations = new ArrayList<>();

        for (int i = 0; i < location.getFreePositions().size()+1; i++) {
            combinations.addAll(Sets.combinations(positions, i));

        }

        for (var comb : combinations) {
            Location newLocation = location.clone();
            Map<Integer, Unit> units = new HashMap<>();

            for (var pos : comb) {
                units.put(pos, new Unit(0, null));
            }

            newLocation.setOurUnits(units);
            locationConverted.add(newLocation);
        }


        return locationConverted;
    }


    public static List<Location> converterOurCase(Location location) {
        // NEED TO BE TESTED
        List<Location> locationConverted = new ArrayList<>();

        Set<Integer> freePositions = new HashSet<>(location.getFreePositions());
        List<Set<Integer>> combinations = new ArrayList<>();

        for (int i = 0; i < location.getFreePositions().size()+1; i++) {
            combinations.addAll(Sets.combinations(freePositions, i));

        }

        for (var comb : combinations) {
            List<Integer> total_positions =  new ArrayList<>(location.getEnemyUnits().keySet());
            total_positions.addAll(comb);

            boolean dropFlag = false;
            for (var pos : total_positions) {
                if (pos > total_positions.size() - 1) {
                    dropFlag = true;
                    break;
                }
            }
            if (dropFlag) continue;

            Location newLocation = location.clone();
            Map<Integer, Unit> units = new HashMap<>();

            for (var pos : comb) {
                units.put(pos, new Unit(0, null));
            }

            newLocation.setOurUnits(units);
            locationConverted.add(newLocation);
        }


        return locationConverted;
    }
}
