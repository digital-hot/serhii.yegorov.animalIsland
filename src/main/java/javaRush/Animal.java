package javaRush;

import java.util.List;
import java.util.Objects;
import java.util.Random;
//TODO: додати створення тварини з випадковими параметрами
public abstract class Animal {
    protected double weight;
    protected int maxCountPerLocation;
    protected int moveSpeed;
    protected double foodNeeded;
    protected double foodEaten;


    private static int nextId = 0;
    private int id;

    public Animal(double weight, int maxCountPerLocation, int moveSpeed, double foodNeeded) {
        this.weight = weight;
        this.maxCountPerLocation = maxCountPerLocation;
        this.moveSpeed = moveSpeed;
        this.foodNeeded = foodNeeded;
        this.id = nextId++;
        this.foodEaten = Math.round(foodNeeded / 2);
    }

    public abstract void eat(Location location);

    public void reproduce(Location location){
        List<Animal> animals = location.getAnimals().stream()
                .filter(animal -> animal.getClass().equals(this.getClass()))
                .filter(animal -> !animal.equals(this))
                .toList();
        if (animals.size() < maxCountPerLocation){
            for (int i = 0; i < animals.size();) {
                    try {
                        location.addAnimal(this.getClass().getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break; // Репродукція лише одн за раз
                //}
            }
        }
    }
    public void move(Location location, Island island){
        Random random = new Random();
        int x = random.nextInt(island.getWidth()-1);
        int y = random.nextInt(island.getHeight()-1);
        island.getLocation(location.getX(), location.getY()).removeAnimal(this);
        island.getLocation(x, y).addAnimal(this);
        //TODO: переробити на випадкове переміщення тварини на одну клітинку
    }

    public double getWeight() {
        return weight;
    }
    public boolean isAlive() {
        return foodEaten > 0;
    }

    public double getFoodEaten() {
        return foodEaten;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public int getId() {
        return id;
    }


    // Метод для симуляції втрати їжі (наприклад, в кінці кожного кроку симуляції)
    public void metabolize() {
        this.foodEaten = this.foodEaten - 1; // Припускаємо, що тварина втрачає 1 одиницю їжі за крок
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return weight == animal.weight && maxCountPerLocation == animal.maxCountPerLocation && moveSpeed == animal.moveSpeed && foodNeeded == animal.foodNeeded && foodEaten == animal.foodEaten;
    }
    @Override
    public int hashCode() {
        return Objects.hash(weight, maxCountPerLocation, moveSpeed, foodNeeded, foodEaten);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "weight=" + weight +
                ", maxCountPerLocation=" + maxCountPerLocation +
                ", moveSpeed=" + moveSpeed +
                ", foodNeeded=" + foodNeeded +
                ", foodEaten=" + foodEaten +
                ", id=" + id +
                '}';
    }
}

