package javaRush;
public class Main {
    public static void main(String[] args) {
        // Створення і запуск симуляції
        Carnivore.readJsonToMap();

        Simulation simulation = new Simulation(100, 20); // Параметри: ширина, висота острова
        simulation.start();
    }
}