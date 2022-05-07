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
        List<Location> locationConverted = new ArrayList<>();
        locationConverted.add(location.clone());

        Optional<Integer> maxEnemyPosition = location.getEnemyUnits().keySet().stream().max(Integer::compare);

        Map<Integer, Unit> minimalUnits = new HashMap<>();

        for (int i = 0; i < maxEnemyPosition.get(); i++) {
            if (!location.getEnemyUnits().containsKey(i)) {
                minimalUnits.put(i, new Unit(0, null));

            }
        }

        Location minLocation = location.clone();
        minLocation.setOurUnits(minimalUnits);
        locationConverted.add(minLocation);

        Map<Integer, Unit> ourUnits = new HashMap<>(minimalUnits);

        for (int i = maxEnemyPosition.get()+1; i < location.getSize(); i++) {
            ourUnits.put(i, new Unit(0, null));

            Map<Integer, Unit> units = new HashMap<>(ourUnits);
            Location newLocation = location.clone();
            newLocation.setOurUnits(units);

            locationConverted.add(newLocation);
        }

        return locationConverted;
    }
}
