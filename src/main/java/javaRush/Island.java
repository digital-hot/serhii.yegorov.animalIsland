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

}
