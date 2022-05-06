package io.deeplay.lab;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreateJson {

    public static void createJson(List<Location> locations) throws Exception {
        var mapper = new ObjectMapper();

        mapper.writeValue(Paths.get("Output_file.json").toFile(), locations);
    }
}

record Location(
        String locationName,
        List<OurUnit> ourUnits
) {
}

record OurUnit(
        String name,
        int locatePosition
) {
}