package old.Json.Organisms;

public class Wolf extends Animal {

    public Wolf(int weight, int maxCountPerCell, int movementSpeed, int foodRequirement) {
        super(weight, maxCountPerCell, movementSpeed, foodRequirement);
    }

    @Override
    public void eat() {
        // Реалізація харчування вовка (наприклад, полювання)
    }

    @Override
    public void reproduce() {
        // Реалізація розмноження вовка
    }

    @Override
    public void move() {
        // Реалізація руху вовка
    }

    @Override
    public void die() {
        // Реалізація умов смерті вовка
    }

    // Можна додати додаткові методи специфічні для вовка
}
