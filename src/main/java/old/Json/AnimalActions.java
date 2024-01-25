package old.Json;

import old.Json.Organisms.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalActions {

    private final ExecutorService executorService;

    public AnimalActions() {
        // Створення пулу із фіксованою кількістю потоків
        this.executorService = Executors.newFixedThreadPool(20);
    }

    public void performActions() {
        // Припустимо, у нас є список тварин
        List<Animal> animals = getAnimals();

        for (Animal animal : animals) {
            executorService.submit(() -> {
                animal.move();
                animal.eat();
                // Інші дії тварин
            });
        }
    }

    private List<Animal> getAnimals() {
        // Отримати список тварин
        return new ArrayList<>();
    }

    public void stopActions() {
    }
}