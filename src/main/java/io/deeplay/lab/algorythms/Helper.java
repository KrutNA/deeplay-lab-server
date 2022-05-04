package io.deeplay.lab.algorythms;

import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
    public static List<Location> converterGeneralCase(Location location) {
        // NEED TO BE TESTED
        List<Location> locationConverted = new ArrayList<>(location.getSize() - location.getEnemyUnits().keySet().size() + 1);

        locationConverted.add(location);

        Map<Integer, Unit> ourUnits = new HashMap<>(location.getOurUnits());
        Unit tempUnit = new Unit(0, null);

        for (int i = 0; i < location.getSize(); i++) {

            if (!location.getEnemyUnits().containsKey(i)) {
                Location newLocation = location.copy();

                ourUnits.put(i, tempUnit);
                newLocation.setOurUnits(new HashMap<>(ourUnits));
                locationConverted.add(newLocation);
            }
        }
        return locationConverted;
    }

    public static List<Location> converterOurCase(Location location) {
        // NEED TO BE TESTED
        List<Location> locationConverted = new ArrayList<>(location.getSize() - location.getEnemyUnits().keySet().size() + 1);

        locationConverted.add(location);

        Map<Integer, Unit> ourUnits = new HashMap<>(location.getOurUnits());
        Unit tempUnit = new Unit(0, null);

        for (int i = 0; i < location.getSize(); i++) {

            if (!location.getEnemyUnits().containsKey(i)) {
                ourUnits.put(i, tempUnit);

                if (location.getEnemyUnits().containsKey(i+1) || (i+1 == location.getSize())) {
                    Location newLocation = location.copy();

                    newLocation.setOurUnits(new HashMap<>(ourUnits));
                    locationConverted.add(newLocation);
                }

            }
        }
        return locationConverted;
    }
}
