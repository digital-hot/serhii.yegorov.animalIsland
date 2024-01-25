package old.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SimulationConfig {
    @JsonProperty("animals")
    private List<AnimalConfig> animals;

    @JsonProperty("plants")
    private List<PlantConfig> plants;

    @JsonProperty("islandSize")
    private IslandSize islandSize;

    // Геттери та сеттери

    public static class AnimalConfig {
        private String type;
        private int count;

        // Геттери та сеттери
    }

    public static class PlantConfig {
        private String type;
        private int count;

        // Геттери та сеттери
    }

    public static class IslandSize {
        private int width;
        private int height;

        // Геттери та сеттери
    }
}
