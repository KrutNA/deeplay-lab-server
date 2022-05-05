package io.deeplay.lab;

import io.deeplay.lab.algorythms.Helper;
import io.deeplay.lab.data.Location;
import io.deeplay.lab.data.LocationReduce;
import io.deeplay.lab.data.Round;
import io.deeplay.lab.data.Unit;
import io.deeplay.lab.db.Database;
import io.deeplay.lab.parser.HistoricalDataParser;
import io.deeplay.lab.parser.RoundFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        Unit unit1 = new Unit(1,"OppUnit1");
        Unit unit2 = new Unit(2,"OppUnit2");
        Unit unit3 = new Unit(3,"OppUnit3");

        Unit unit4 = new Unit(4,"OurUnit1");

        Map<Integer,Unit> opponentsUnit = new HashMap<>();

//        opponentsUnit.put(0,unit1);
//        opponentsUnit.put(1,unit2);
//        opponentsUnit.put(2,unit3);

        LocationReduce location = new LocationReduce(1, (short)6, opponentsUnit);


        List<Location> locations = Helper.converterGeneralCase(location);

        for(Location location1 : locations){
            System.out.println(location1.getOurUnits());
        }

    }
}

