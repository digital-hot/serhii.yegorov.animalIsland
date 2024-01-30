package javaRush;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivore extends Animal {

    public Carnivore(int weight, int maxCountPerLocation, int moveSpeed, int foodNeeded) {
        super(weight, maxCountPerLocation, moveSpeed, foodNeeded);
    }

    public void eat(Location location) {
        List<Animal> potentialPrey = new ArrayList<>(location.getAnimals());

        for (Animal prey : potentialPrey) {
            Map<String, Integer> predator = DateAnimals.huntingProbabilities.get(this.getClass().getSimpleName());
            if (this != prey && this.foodEaten < this.foodNeeded) {
                int chance = predator.getOrDefault(prey.getClass().getSimpleName(), 0);
                if (chance > 0 && ThreadLocalRandom.current().nextInt(100) < chance) {
                    this.foodEaten += Math.min(prey.getWeight(), this.foodNeeded);
                    location.removeAnimal(prey);
                    break; // Хижак їсть лише одну жертву за раз
                }
            }
        }
    }
}

