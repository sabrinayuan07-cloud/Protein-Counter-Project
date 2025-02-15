package model;

import java.util.HashMap;
import java.util.Map;

public class MealPlan {
    // key is Food, value is how many 100grams of food eaten
    private HashMap<Food, Integer> foodEaten;

    public MealPlan() {
        foodEaten = new HashMap<Food, Integer>();
    }

    // REQUIRES: foodEaten not null
    // MODIFIES: 
    // EFFECTS: returns total protein intake calculated
    private double calculateProtein() {

    }
}
