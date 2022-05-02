package io.deeplay.lab;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

class CreateJsonTest {
    @Test
    void name() throws Exception {
        OurUnit unit1 = new OurUnit("Player_1",2);
        OurUnit unit2 = new OurUnit("Player_2",4);
        OurUnit unit3 = new OurUnit("Player_3",5);
        OurUnit unit4 = new OurUnit("Player_4",1);

        ArrayList<OurUnit> unitsLocation1 = new ArrayList<>();
        unitsLocation1.add(unit1);
        unitsLocation1.add(unit2);

        ArrayList<OurUnit> unitsLocation2 = new ArrayList<>();
        unitsLocation2.add(unit3);
        unitsLocation2.add(unit4);

        Location location1 = new Location("Location_1",unitsLocation1);
        Location location2 = new Location("Location_2",unitsLocation2);

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);

        CreateJson.createJson(locations);

        String goodFilePath = "src/test/resources/Output_file.json";
        String createdFilePath = "Output_file.json";

        try( InputStream isCreated = new FileInputStream(createdFilePath);
             InputStream isGood = new FileInputStream(goodFilePath);) {


            var mapper = new ObjectMapper();
            var parserCreated = mapper.getFactory().createParser(isCreated);

            var parserGood = mapper.getFactory().createParser(isGood);

            if (parserCreated.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Stream has already been read");
            }

            if (parserGood.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Stream has already been read");
            }

            while (parserCreated.nextToken() != JsonToken.END_ARRAY) {
                parserGood.nextToken();
                Location locationCreated = mapper.readValue(parserCreated, Location.class);
                Location locationGood = mapper.readValue(parserGood, Location.class);

                Assertions.assertEquals(locationGood, locationCreated);
            }

        }
        File file = new File(createdFilePath);
        if  (file.exists()){
            file.delete();
        }
    }
}