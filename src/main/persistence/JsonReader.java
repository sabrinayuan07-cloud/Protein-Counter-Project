package persistence;

import model.Food;
import model.MealPlan;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;

import org.json.*;

// SOURCE: FROM JSON LIBRAIRIES SAMPLE APPLICATION

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source; 
    }

    public MealPlan readMealPlan(String source) throws IOException {
        return null;
        // stub
    }

    // EFFECTS: reads food from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Food read() throws IOException {
        return null;
        // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
        // stub
    }

    // EFFECTS: parses food from JSON object and returns it
    private Food parseFood(JSONObject jsonObject) {
        return null;
        // stub
    }

    // MODIFIES: meal plan
    // EFFECTS: parses food from JSON object and adds them to mealplan
    private void addFood(Food food, JSONObject jsonObject) {
       // stub
    }
}
