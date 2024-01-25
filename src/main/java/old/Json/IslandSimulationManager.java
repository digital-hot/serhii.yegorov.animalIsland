package old.Json;

public class IslandSimulationManager {

    private final IslandSimulation islandSimulation;
    private final AnimalActions animalActions;

    public IslandSimulationManager() {
        this.islandSimulation = new IslandSimulation();
        this.animalActions = new AnimalActions();
        // Ініціалізація інших компонентів за потреби
    }

    public void start() {
        // Запуск симуляції
        islandSimulation.startSimulation();
        animalActions.performActions();
        initializeSimulation("src/main/resources/config.json");
        // Можна додати додаткову логіку для запуску та управління симуляцією
    }

    public void stop() {
        // Зупинка симуляції та закриття ресурсів
        islandSimulation.stopSimulation();
        animalActions.stopActions();

        // Закриття інших ресурсів та очищення за потреби
    }

    public static void main(String[] args) {
        IslandSimulationManager manager = new IslandSimulationManager();
        manager.start();


        // Додайте код для контролю та взаємодії з симуляцією (наприклад, через консоль)
    }

    // ... інші частини класу ...

    public void initializeSimulation(String configFilePath) {
        SimulationConfig config = ConfigLoader.loadConfig(configFilePath);

        if (config != null) {
  /*          initializeIsland(config.getIslandSize());
            initializeAnimals(config.getAnimals());
            initializePlants(config.getPlants());
*/        }
    }

    /*public void initializeSimulation(String configFilePath) {
        JSONObject config = ConfigLoader.loadConfig(configFilePath);

        if (config != null) {
            initializeIsland((JSONObject) config.get("islandSize"));
            initializeAnimals((JSONArray) config.get("animals"));
            initializePlants((JSONArray) config.get("plants"));
        }
    }*/

   /* private void initializeIsland(JSONObject islandSize) {
        int width = ((Integer) islandSize.get("width")).intValue();
        int height = ((Integer) islandSize.get("height")).intValue();
        // Створення острова з розміром width x height
    }*/

    /*private void initializeAnimals(JSONArray animals) {
        for (Object o : animals) {
            JSONObject animal = (JSONObject) o;
            String type = (String) animal.get("type");
            int count = ((Long) animal.get("count")).intValue();
            // Створення тварин за типом та кількістю
        }
    }*/

    /*private void initializePlants(JSONArray plants) {
        for (Object o : plants) {
            JSONObject plant = (JSONObject) o;
            String type = (String) plant.get("type");
            int count = ((Long) plant.get("count")).intValue();
            // Створення рослин за типом та кількістю
        }
    }*/
    // Методи для ініціалізації острова, тварин і рослин
}
