package javaRush;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Simulation {
    private Island island;
    private int simulationSteps;

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
        //listClassesAnimals.forEach(System.out::println);

    }


    public Simulation(int width, int height, int simulationSteps) {
        this.island = new Island(width, height);
        this.simulationSteps = simulationSteps;
        initializeIsland();
    }

    // Метод для ініціалізації острова з початковими тваринами та рослинами
    private void initializeIsland() {
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
                        for (int k = 0; k < random.nextInt(200); k++) {
                            Plant plant = new Plant(1); // Припустимо, що всі рослини мають вагу 1
                            island.getLocation(i, j).addPlant(plant);
                        }
                    }
                }
            }
        }
    }

    // Запуск симуляції
    public void start() {
        for (int step = 0; step < simulationSteps; step++) {
            displayIslandState();
            System.out.println("javaRush.Simulation Step: " + (step + 1));
            island.update();
            //displayIslandState(); // Виведення стану острова
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

                for (Animal animal : location.getAnimals()) {
                    if (animal instanceof Wolf) {
                        wolfCount++;
                    } else if (animal instanceof Horse) {
                        horseCount++;
                    }
                    System.out.println("Адреса "+i+":"+j+" "+animal.getId()+" "+animal.getClass().getSimpleName() + " вага " + animal.getWeight());
                    // ... перевірка інших видів тварин ...
                }

                plantCount += location.getPlants().size();
            }
        }

        System.out.println("Стан острова:");
        System.out.println("Вовків: " + wolfCount);
        System.out.println("Коней: " + horseCount);
        // ... виведення інформації про інших тварин ...
        System.out.println("Рослин: " + plantCount);
    }

}
