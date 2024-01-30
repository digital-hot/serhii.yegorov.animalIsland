package javaRush;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivore extends Animal {
    private static HashMap<String, Map<String, Integer>> huntingProbabilities;

    public Carnivore(int weight, int maxCountPerLocation, int moveSpeed, int foodNeeded) {
        super(weight, maxCountPerLocation, moveSpeed, foodNeeded);
    }

    public static void readJsonToMap() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            huntingProbabilities = mapper.readValue(new File("src/main/resources/data.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void eat(Location location) {
        List<Animal> potentialPrey = new ArrayList<>(location.getAnimals());

        for (Animal prey : potentialPrey) {
            Map<String, Integer> predator = huntingProbabilities.get(this.getClass().getSimpleName());
            if (this != prey) {
                int chance = predator.getOrDefault(prey.getClass().getSimpleName(), 0);
                if (ThreadLocalRandom.current().nextInt(100) < chance) {
                    this.foodEaten += Math.min(prey.getWeight(), this.foodNeeded);
                    location.removeAnimal(prey);
                    break; // Хижак їсть лише одну жертву за раз
                }
            }
        }
    }
}

