package old.Json.Organisms;

public abstract class Animal {
    protected int weight;
    protected int maxCountPerCell;
    protected int movementSpeed;
    protected int foodRequirement;

    public Animal(int weight, int maxCountPerCell, int movementSpeed, int foodRequirement) {
        this.weight = weight;
        this.maxCountPerCell = maxCountPerCell;
        this.movementSpeed = movementSpeed;
        this.foodRequirement = foodRequirement;
    }

    public abstract void eat();
    public abstract void reproduce();
    public abstract void move();
    public abstract void die();


}
