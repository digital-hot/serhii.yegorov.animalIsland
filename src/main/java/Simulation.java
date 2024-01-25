import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Random;
import java.util.Set;

public class Simulation {
    private Island island;
    private int simulationSteps;

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
//                Reflections reflections = new Reflections("Animal", new SubTypesScanner());
//
//                // Знаходження всіх класів, що наслідуються від Animal
//                Set<Class<? extends Animal>> subTypes = reflections.getSubTypesOf(Animal.class);
//
//                for (Class<? extends Animal> subType : subTypes) {
//                    System.out.println(subType.getName());
//                }
                if (random.nextDouble() < 0.1) { // 10% шанс, що у локації буде тварина або рослина
                    if (random.nextBoolean()) {
                        // Розміщуємо тварину
                        Animal animal = getRandomAnimal(random);
                        island.getLocation(i, j).addAnimal(animal);
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

    private Animal getRandomAnimal(Random random) {
        // Припустимо, що у нас є 5 видів тварин
        switch (random.nextInt(2)) {
            case 0: return new Wolf();
            case 1: return new Horse();
//            case 2: return new Rabbit();
//            case 3: return new Bear();
//            case 4: return new Fox();
            default: return new Wolf();/*Rabbit();*/ // Використовуємо Rabbit як запасний варіант
        }
    }
    // Запуск симуляції
    public void start() {
        for (int step = 0; step < simulationSteps; step++) {
            System.out.println("Simulation Step: " + (step + 1));
            island.update();
            displayIslandState(); // Виведення стану острова
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

    public static void main(String[] args) {
        // Створення і запуск симуляції
        Simulation simulation = new Simulation(100, 20, 10); // Параметри: ширина, висота острова, кількість кроків симуляції
        simulation.start();
    }
}
