package javaRush;

import java.util.ArrayList;

public class Island {
    private Location[][] locations;
    private int width;
    private int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[width][height];
        initializeLocations();
    }

    // Ініціалізація всіх локацій на острові
    private void initializeLocations() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location();
                locations[i][j].setX(i);
                locations[i][j].setY(j);
            }
        }
    }

    // Отримання локації за координатами
    public Location getLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return locations[x][y];
        }
        return null; // Або можна кинути виняток, якщо координати поза межами острова
    }

    // Метод для оновлення стану острова (наприклад, для пересування тварин, росту рослин тощо)
    public void update() {
        // Прохід по всім локаціям острова
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Location location = locations[i][j];

                // Оновлення стану тварин в кожній локації
                updateAnimals(location);

                // Оновлення стану рослин (якщо потрібно)
                //updatePlants(location);
            }
        }
    }

    private void updateAnimals(Location location) {
        // Тут можна додати логіку для оновлення стану тварин
        // На приклад, переміщення, їжа, розмноження, перевірка на виживання тощо
        for (Animal animal : new ArrayList<>(location.getAnimals())) {
            if (animal instanceof Herbivore) {
                ((Herbivore) animal).eat(location);
            }
            if (animal instanceof Carnivore) {
                ((Carnivore) animal).eat(location);
            }
            animal.reproduce(location);
            animal.move(location, this);
        }
    }

    private void updatePlants(Location location) {
        //Первірка2
        // Тут можна додати логіку для оновлення стану рослин
        // На приклад, ріст рослин, зміна їх кількості тощо
        for (Plant plant : new ArrayList<>(location.getPlants())) {
            // Логіка оновлення рослин
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Додаткові методи, якщо потрібно, наприклад, для виведення статистики острова
}
