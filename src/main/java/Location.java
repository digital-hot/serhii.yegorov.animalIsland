import java.util.ArrayList;
import java.util.List;

public class Location {
    private List<Animal> animals;
    private List<Plant> plants;
    private int x;
    private int y;

    public Location() {
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    // Метод для додавання тварини в локацію
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Метод для видалення тварини з локації
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    // Метод для отримання списку тварин в локації
    public List<Animal> getAnimals() {
        return animals;
    }

    // Метод для додавання рослини в локацію
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    // Метод для видалення рослини з локації
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    // Метод для отримання списку рослин в локації
    public List<Plant> getPlants() {
        return plants;
    }

    public void setY(int j) {
        this.y = j;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Методи для інших дій в локації, наприклад, пошук їжі, парування тварин тощо
}
