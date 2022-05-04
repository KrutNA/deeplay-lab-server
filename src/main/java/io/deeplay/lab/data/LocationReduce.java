package io.deeplay.lab.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationReduce implements Location {
    final int id;
    final short size;
    final Map<Integer, Unit> opponentUnits;
    private Map<Integer, Unit> ourUnits;

    public int getId() {
        return id;
    }

    public short getSize() {
        return size;
    }

    public int getUnitCount() {
        return ourUnits.size() + opponentUnits.size();
    }

    public Map<Integer, Unit> getEnemyUnits() {
        return opponentUnits;
    }

    public void setOurUnits(Map<Integer, Unit> ourUnits) {
        this.ourUnits = ourUnits;
    }

    public Map<Integer, Unit> getOurUnits() {
        return ourUnits;
    }

    public LocationReduce copy() {
        return new LocationReduce(id, size, opponentUnits);
    }

    LocationReduce(int id, short size, Map<Integer, Unit> opponentUnits) {
        this.id = id;
        this.size = size;
        this.opponentUnits = opponentUnits;
        this.ourUnits = new HashMap<>();
    }

}
