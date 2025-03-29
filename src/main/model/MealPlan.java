package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents food eaten and calculates its total protein intake
public class MealPlan implements Writable {
    // key is Food, value is how many 100grams of food eaten
    private HashMap<Food, Double> foodEaten;
    private String name;
    private double proteinGoal;
    private Event eventAddFood;

    public MealPlan(String name, double proteinGoal) {
        foodEaten = new HashMap<Food, Double>();
        this.name = name; // name of user
        this.proteinGoal = proteinGoal; // protein goal for each day
    }

    // EFFECTS: returns nb of grams eaten of X food, if food has not been eaten,
    // return 0
    public double getFoodQuantity(Food food) {
        if (!foodEaten.containsKey(food)) {
            return 0;
        } else {
            return foodEaten.get(food);
        }
    }

    // EFFECTS: returns list of all Food items that have been eaten
    // if empty, returns empty list
    public ArrayList<Food> getAllFoodEaten() {
        return new ArrayList<Food>(foodEaten.keySet());
    }

    // REQUIRES: food != null and grams > 0
    // MODIFIES: this
    // EFFECTS: adds Food item to the HashMap of today's meals
    public void addFood(Food food, double grams) {
        if (grams > 0) {
            if (foodEaten.containsKey(food)) {
                foodEaten.put(food, foodEaten.get(food) + grams);
            } else {
                foodEaten.put(food, grams);
            }
            EventLog.getInstance().logEvent(new Event(grams + "g of " + food.getName() + " added to meal plan"));
        }
    }

    // MODIFIES: this
    // EFFECTS: calculates protein intake of the food eaten and adds it to the total
    // protein intake of the day
    // returns total protein intake of the day
    public double calculateProtein() {
        double totalProtein = 0;
        for (Map.Entry<Food, Double> item : foodEaten.entrySet()) {
            totalProtein += (item.getKey().getProteinCountPerHundredGrams() / 100) * item.getValue();
        }
        return totalProtein;
    }

    // REQUIRES: proteinGoal > 0
    // MODIFIES: this
    // EFFECTS: outputs protein progress (in %) achieved based on proteinGoal
    public int getProgressPourcentage() {
        int progress = (int) Math.round((calculateProtein() / getProteinGoal()) * 100);
        if (progress > 100) {
            return 100;
        } else {
            return progress;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Set user name to: " + name));
    }

    public double getProteinGoal() {
        return proteinGoal;
    }

    public void setProteinGoal(int proteinGoal) {
        this.proteinGoal = proteinGoal;
        EventLog.getInstance().logEvent(new Event("Set protein goal to: " + proteinGoal));
    }

    // EFFECTS: returns things in this mealplan as a JSON array
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Food, Double> item : foodEaten.entrySet()) {
            JSONObject foodEatenJson = new JSONObject();
            JSONObject foodJson = item.getKey().toJson();
            // JSONObject foodJson = new JSONObject();
            // foodJson.put("name", item.getKey().getName());
            // foodJson.put("proteinCountPerHundredGrams",
            // item.getKey().getProteinCountPerHundredGrams());
            foodEatenJson.put("food", foodJson);
            foodEatenJson.put("gramsEaten", item.getValue());
            jsonArray.put(foodEatenJson);
        }
        jsonObject.put("foodEaten", jsonArray);
        jsonObject.put("name", this.name);
        jsonObject.put("proteinGoal", this.proteinGoal);
        return jsonObject;
    }
}
