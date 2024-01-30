package javaRush;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DateAnimals {

    public static final Set<Class<? extends Animal>> listClassesAnimals = CreateListAnimalClasses();

    public static final HashMap<String, Map<String, Integer>> huntingProbabilities = readJsonToMap();
    private static Set<Class<? extends Animal>> CreateListAnimalClasses() {
        Set<Class<? extends Animal>> listClassesAnimals = new HashSet<>();
        Reflections reflectionsCarnivore = new Reflections(Carnivore.class);

        for (Class<? extends Carnivore> predator : reflectionsCarnivore.getSubTypesOf(Carnivore.class)) {
            listClassesAnimals.add(predator);
        }

        Reflections reflectionsHerbivore = new Reflections(Herbivore.class);
        for (Class<? extends Herbivore> herbivorous : reflectionsHerbivore.getSubTypesOf(Herbivore.class)) {
            listClassesAnimals.add(herbivorous);
        }

        return listClassesAnimals;
    }
    public static HashMap<String, Map<String, Integer>> readJsonToMap() {
        HashMap<String, Map<String, Integer>> huntingProbabilities = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("src/main/resources/predationProbabilityTable.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return huntingProbabilities;
    }

    //    public static List<String> loadAnimalCharacteristics(String fileName) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Map<String, List<String>> data = mapper.readValue(new File(fileName),
//                    new com.fasterxml.jackson.core.type.TypeReference<Map<String, List<String>>>() {});
//            return data.get("animal_characteristics");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}

