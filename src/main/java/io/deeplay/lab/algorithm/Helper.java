package io.deeplay.lab.algorithm;

import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.Unit;

import java.util.*;

public class Helper {

    public static List<Location> converterOurCase(Location location) {
        List<Location> locationConverted = new ArrayList<>();

        Optional<Integer> maxEnemyPosition = location.getEnemyUnits().keySet().stream().max(Integer::compare);

        Map<Integer, Unit> minimalUnits = new HashMap<>();

        for (int i = 0; i < maxEnemyPosition.get(); i++) {
            if (!location.getEnemyUnits().containsKey(i)) {
                minimalUnits.put(i, new Unit(0, null));

            }
        }
        if (!minimalUnits.isEmpty()) {
            locationConverted.add(location.clone());
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
