package javaRush;

public class Plant {
    private int weight; // Вага рослини
    private static final int MAX_COUNT_PER_LOCATION = 200; // Максимальна кількість рослин в одній локації

    public Plant(int weight) {
        this.weight = weight;
    }

    // Метод для отримання ваги рослини
    public int getWeight() {
        return weight;
    }

    // Статичний метод для отримання максимальної кількості рослин в локації
    public static int getMaxCountPerLocation() {
        return MAX_COUNT_PER_LOCATION;
    }

    // Методи для імітації росту рослини, якщо потрібно
    public void grow() {
        // Логіка росту рослини
    }
}
