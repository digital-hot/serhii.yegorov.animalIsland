package javaRush;
public class Main {
    public static void main(String[] args) {
        // Створення і запуск симуляції
        Simulation simulation = new Simulation(100, 20); // Параметри: ширина, висота острова, кількість кроків симуляції
        simulation.start();
    }
}