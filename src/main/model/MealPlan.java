package model;

import java.util.HashMap;
import java.util.Map;

// Represents food eaten and calculates its total protein intake
public class MealPlan {
    // key is Food, value is how many 100grams of food eaten
    private HashMap<Food, Double> foodEaten;

    public MealPlan() {
        foodEaten = new HashMap<Food, Double>();
    }

    // REQUIRES: food != null
    // MODIFIES: this
    // EFFECTS: adds Food item to the HashMap of today's meals - if food is already in foodEaten, 
    // then don't do anything, if not: add Food item into foodEaten
    private void addFood(Food foodItem) {
        // stub
    }

    // REQUIRES: value > 0
    // MODIFIES: this
    // EFFECTS: returns total protein intake calculated
    private double calculateProtein() {
        // stub
        return 0;
    }
}
