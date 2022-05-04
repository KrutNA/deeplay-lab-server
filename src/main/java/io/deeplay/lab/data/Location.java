package io.deeplay.lab.data;

import java.util.Map;

public interface Location {

    public int getId();

    public short getSize();

    public int getUnitCount();

    public Map<Integer, Unit> getEnemyUnits();

    public Map<Integer, Unit> getOurUnits();

    public void setOurUnits(Map<Integer, Unit> ourUnits);

    public Location copy();

}
