package old.Json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class ConfigLoader {
    public static SimulationConfig loadConfig(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), SimulationConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

