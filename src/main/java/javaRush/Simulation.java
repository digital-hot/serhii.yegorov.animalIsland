package javaRush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final Island island;
    private int stateIsland;
    private int countAnimals;

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
                for (Class<? extends Animal> classAnimal : DateAnimals.listClassesAnimals) {
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

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable updateTask = new Runnable() {
            public void run() {
                System.out.println("День: " + (++stateIsland));
                updateIsland();
                displayIslandState(); // Виведення стану острова

                if (countAnimals == 0 || stateIsland > 5) {
                    scheduler.shutdown();
                }
            }
        };

        // Запуск задачі з інтервалом 10 секунда (або іншим необхідним інтервалом)
        scheduler.scheduleAtFixedRate(updateTask, 0, 10, TimeUnit.SECONDS);

        /*for (int i = 0; i < 5; i++) {
            System.out.println("День: " + (stateIsland++));
            updateIsland();
            displayIslandState(); // Виведення стану острова
        }*/
    }

    public void updateIsland() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Прохід по всім локаціям острова
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                final Location location = island.getLocation(i, j);
                executor.submit(() -> {
                    updateAnimals(location);
                    updatePlants(location);
                });
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

  /*      // Прохід по всім локаціям острова
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Location location = island.getLocation(i,j);

                // Оновлення стану тварин в кожній локації
                updateAnimals(location);

                // Оновлення стану рослин (якщо потрібно)
                updatePlants(location);
            }
        }*/
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
            } else {
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

        Map<String, Long> animalCount = new HashMap<>();
        long plantCount = 0;

        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Location location = island.getLocation(i, j);
                int locationCountPlant = location.getPlants().size();
                plantCount += locationCountPlant;

                int finalI = i;
                int finalJ = j;
                location.getAnimals().stream()
                        .forEach(animal -> {
                            animalCount.merge(animal.getClass().getSimpleName(), 1L, Long::sum);
                            System.out.println("День:" + stateIsland + "; локація: " + finalI + ":" + finalJ + "; " + "рослин: " + locationCountPlant + "; " + animal.getId()
                                    + " " + animal.getClass().getSimpleName() + "; насиченість: " + animal.getFoodEaten() + " потрібно їжі: "
                                    + animal.getFoodNeeded() + "; вага " + animal.getWeight());
                        });
            }
        }

        System.out.println("Стан острова день:" + stateIsland);
        animalCount.forEach((animal, count) -> System.out.println(animal + ": " + count));
        System.out.println("Рослин: " + plantCount);
        countAnimals = animalCount.size();
    }

}
