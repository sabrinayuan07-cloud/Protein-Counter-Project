package model;

import java.util.HashMap;
import java.util.Map;

// Represents food eaten and calculates its total protein intake
public class MealPlan {
    // key is Food, value is how many 100grams of food eaten
    private HashMap<Food, Double> foodEaten;
    private String name;
    private double proteinGoal;

    public MealPlan(String name, double proteinGoal) {
        foodEaten = new HashMap<Food, Double>();
        this.name = name; // name of user
        this.proteinGoal = proteinGoal; // protein goal for each day
    }

    public int getFoodQuantity(Food food) {
        return 0;
        // stub
    }

    // REQUIRES: food != null and quantity > 0
    // MODIFIES: this
    // EFFECTS: adds Food item to the HashMap of today's meals
    public void addFood(Food foodItem, double quantity) {
        // stub
    }

    // REQUIRES: value > 0
    // MODIFIES: this
    // EFFECTS: calculates protein intake of the food eaten and adds it to the total protein intake of the day
    // returns total protein intake of the day
    public double calculateProtein() {
        // stub
        // for each food in hashmap:
        // calls getProteinCountPerHundredGrams() and times it by quantity to get protein intake of food
        // total += protein of that food
        return 0;
    }

    // REQUIRES: proteinGoal > 0
    // MODIFIES: this
    // EFFECTS: outputs protein progress (in %) achieved based on proteinGoal
    public int getProgressPourcentage() {
        return 0;
        // stub
    }

    public String getName() {
        return "";
        // stub
    }

    public void setName(String name) {
        this.name = "";
        // stub
    }

    public double getProteinGoal() {
        return 0;
        // stub
    }

    public void setProteinGoal(int proteinGoal) {
        this.proteinGoal = 0;
        // stub
    }
}
