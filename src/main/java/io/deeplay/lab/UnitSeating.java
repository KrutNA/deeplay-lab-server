package io.deeplay.lab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class UnitSeating {
    static Random rnd = new Random();

    // предсказание
    static public double predict(Location2 location2, List<Integer> position) {
        return rnd.nextDouble();
    }

    // Всевозможная рассадка юнитов на локации
    static public List<Seating> seatings(Location2 location2) {

        List<Seating> result = new ArrayList<>();
        List<Integer> freePlaces = getFreePlaces(location2);

        if (getFreePlaces(location2).isEmpty()){
            return new ArrayList<>();
        }

        for (Integer i : freePlaces) {
            ArrayList<OurUnit> tempOurUnits = new ArrayList<>(location2.ourUnits());

            tempOurUnits.add(new OurUnit("name", i));

            Location2 tempLocation = new Location2(location2.locationName(),tempOurUnits,location2.opponetsUnits(),location2.locateSize());

            ArrayList<Integer> busyPosition = new ArrayList<>();

            for (OurUnit j : tempOurUnits) {
                busyPosition.add(j.locatePosition());
            }

            result.add(new Seating(tempLocation,busyPosition, predict(location2, busyPosition)));

            result.addAll(seatings(tempLocation));
        }

        return result;

    }

    //массив с пустыми местами
    static public List<Integer> getFreePlaces(Location2 location2) {
        List<Integer> temp = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < location2.locateSize(); i++) {
            temp.add(i);
        }

        for (OurUnit i : location2.opponetsUnits()) {
            temp.set(i.locatePosition(),-1);
        }
        for (OurUnit i : location2.ourUnits()) {
            temp.set(i.locatePosition(),-1);
        }

        for (int i = 0; i < location2.locateSize(); i++) {
            if (temp.get(i)!=-1){
                result.add(i);
            }
        }

        return result;
    }
}


record Location2(
        String locationName,
        List<OurUnit> ourUnits,
        List<OurUnit> opponetsUnits,
        int locateSize
) {
}

record Seating(
        Location2 location2,
        List<Integer> locatePositions,
        double profitPredict
) {
}