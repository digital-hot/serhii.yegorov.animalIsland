package javaRush;

import org.reflections.Reflections;
import java.util.*;

public class Simulation {
    private final Island island;
    private int stateIsland;


    private static final Set<Class<? extends Animal>> listClassesAnimals = new HashSet<>();

    static {
        // Ініціалізація таблиці для всіх тварин

        Reflections reflectionsCarnivore = new Reflections(Carnivore.class);
        for (Class<? extends Carnivore> predator : reflectionsCarnivore.getSubTypesOf(Carnivore.class)) {
            listClassesAnimals.add(predator);
        }
        Reflections reflectionsHerbivore = new Reflections(Herbivore.class);
        for (Class<? extends Herbivore> herbivorous : reflectionsHerbivore.getSubTypesOf(Herbivore.class)) {
            listClassesAnimals.add(herbivorous);
        }

    }


    public Simulation(int width, int height) {
        this.island = new Island(width, height);
        initializeIsland();
    }

    // Метод для ініціалізації острова з початковими тваринами та рослинами
    private void initializeIsland() {
        System.out.println("Ініціалізація:");
        Random random = new Random();

        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                for (Class<? extends Animal> classAnimal : listClassesAnimals) {
                    if (random.nextDouble() < 0.1) { // 10% шанс, що у локації буде тварина або рослина
                        if (random.nextBoolean()) {
                            // Розміщуємо тварину
                            try {
                                island.getLocation(i, j).addAnimal(classAnimal.getDeclaredConstructor().newInstance());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        // Розміщуємо рослину
                        for (int k = 0; k < random.nextInt(Plant.getMaxCountPerLocation()); k++) {
                            Plant plant = new Plant(1); // Припустимо, що всі рослини мають вагу 1
                            island.getLocation(i, j).addPlant(plant);
                        }
                    }
                }
            }
        }
        displayIslandState();
    }

    // Запуск симуляції
    public void start() {
        for (int i = 0; i < 5; i++) {
            System.out.println("День: " + (stateIsland++));
            updateIsland();
            displayIslandState(); // Виведення стану острова
        }
    }


    public void updateIsland() {
        // Прохід по всім локаціям острова
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Location location = island.getLocation(i,j);

                // Оновлення стану тварин в кожній локації
                updateAnimals(location);

                // Оновлення стану рослин (якщо потрібно)
                updatePlants(location);
            }
        }
    }

    // Метод для оновлення стану острова (наприклад, для пересування тварин, росту рослин тощо)
    private void updateAnimals(Location location) {
        for (Animal animal : new ArrayList<>(location.getAnimals())) {
            if (animal instanceof Herbivore) {
                animal.eat(location);
            }
            if (animal instanceof Carnivore) {
                animal.eat(location);
            }
            animal.reproduce(location);
            animal.metabolize();
            if (!animal.isAlive()) {
                location.removeAnimal(animal);
            }else {
                animal.move(location, island);
            }

        }
    }

    private void updatePlants(Location location) {

        Random random = new Random();
        // Розміщуємо рослину
        if (Plant.getMaxCountPerLocation() > location.getPlants().size()) {
            for (int k = 0; k < random.nextInt(Plant.getMaxCountPerLocation() - location.getPlants().size()); k++) {
                Plant plant = new Plant(1); // Припустимо, що всі рослини мають вагу 1
                location.addPlant(plant);
            }
        }
    }

    // Метод для виведення стану острова на кожному кроці симуляції
    private void displayIslandState() {
        int wolfCount = 0;
        int horseCount = 0;
        // ... інші змінні для різних видів тварин ...

        int plantCount = 0;

        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Location location = island.getLocation(i, j);

                int locationCountPlant = location.getPlants().size();
                for (Animal animal : location.getAnimals()) {
                    if (animal instanceof Wolf) {
                        wolfCount++;
                    } else if (animal instanceof Horse) {
                        horseCount++;
                    }
                    System.out.println("День:"+stateIsland+"; локація: "+i+":"+j+"; "+"рослин: "+locationCountPlant+"; "+animal.getId()
                            +" "+animal.getClass().getSimpleName() +"; насиченість: "+ animal.getFoodEaten()+" потрібно їжі: "
                            +animal.getFoodNeeded()+"; вага " + animal.getWeight());
                    // ... перевірка інших видів тварин ...
                }

                plantCount += location.getPlants().size();
            }
        }

        System.out.println("Стан острова день:" + stateIsland);
        System.out.println("Вовків: " + wolfCount);
        System.out.println("Коней: " + horseCount);
        // ... виведення інформації про інших тварин ...
        System.out.println("Рослин: " + plantCount);
    }

}
