import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivore extends Animal{
    public Carnivore(int weight, int maxCountPerLocation, int moveSpeed, int foodNeeded) {
        super(weight, maxCountPerLocation, moveSpeed, foodNeeded);
    }

    private static final Map<Class<? extends Animal>, Integer> huntingProbabilities = new HashMap<>();

    static {
        // Ініціалізація таблиці для всіх хижаків
        huntingProbabilities.put(Horse.class, 60); // Вовк: 60% шанс з'їсти кролика
        //huntingProbabilities.put(Deer.class, 15); // Вовк: 15% шанс з'їсти оленя
        // Додайте інші комбінації хижак-жертва
    }


    public void eat(Location location) {
        List<Animal> potentialPrey = new ArrayList<>(location.getAnimals());

        for (Animal prey : potentialPrey) {
            if (this != prey && canEat(prey)) {
                int chance = huntingProbabilities.getOrDefault(prey.getClass(), 0);
                if (ThreadLocalRandom.current().nextInt(100) < chance) {
                    this.foodEaten += prey.getWeight();
                    location.removeAnimal(prey);
                    break; // Хижак їсть лише одну жертву за раз
                }
            }
        }
    }

    protected boolean canEat(Animal prey) {
        // Перевірка, чи є тварина потенційною здобиччю
        return huntingProbabilities.containsKey(prey.getClass());
    }

    // Інші методи, специфічні для хижаків
}

