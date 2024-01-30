package javaRush;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxCountPerLocation, int moveSpeed, double foodNeeded) {
        super(weight, maxCountPerLocation, moveSpeed, foodNeeded);
    }

    @Override
    public void eat(Location location) {
        List<Plant> plants = location.getPlants();
        if (!plants.isEmpty()) {
            while (plants.iterator().hasNext()){
                if (this.foodEaten >= this.foodNeeded) {
                    break;
                }
                Plant plant = plants.iterator().next();
                this.foodEaten += plant.getWeight();
                plants.remove(plant);// Видаляємо рослину з локації після її "з'їдання"
            }
        }
    }

}
