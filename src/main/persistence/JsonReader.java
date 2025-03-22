package persistence;

import model.Food;
import model.MealPlan;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;

// SOURCE: FROM JSON LIBRAIRIES SAMPLE APPLICATION DEMO

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Reads MealPlan from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MealPlan read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMealPlan(jsonObject);
    }

    // EFFECTS: Reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses MealPlan from JSON object and returns it
    private MealPlan parseMealPlan(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double proteinGoal = jsonObject.getDouble("proteinGoal");
        MealPlan mp = new MealPlan(name, proteinGoal);
        addFoods(mp, jsonObject);
        return mp;
    }

    // MODIFIES: mp
    // EFFECTS: parses food from JSON object and adds them to mealplan
    private void addFoods(MealPlan mp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodEaten");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(mp, nextFood);
        }
    }

    // MODIFIES: mp
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addFood(MealPlan mp, JSONObject jsonObject) {
        JSONObject foodObject = jsonObject.getJSONObject("food");
        String foodName = foodObject.getString("name");
        double proteinPerHundredGrams = foodObject.getDouble("proteinCountPerHundredGrams");
        int gramsEaten = jsonObject.getInt("gramsEaten");
        Food food = new Food(foodName, proteinPerHundredGrams);
        mp.addFood(food, gramsEaten);
    }
}
