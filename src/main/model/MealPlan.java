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

    public double getFoodQuantity(Food food) {
        if (!foodEaten.containsKey(food)) {
            return 0;
        } else {
            return foodEaten.get(food);
        }
    }

    // REQUIRES: food != null and quantity > 0
    // MODIFIES: this
    // EFFECTS: adds Food item to the HashMap of today's meals
    public void addFood(Food food, double quantity) {
        if (foodEaten.containsKey(food)) {
            foodEaten.put(food, foodEaten.get(food) + quantity);
        } else {
            foodEaten.put(food, quantity);
        }
    }

    // REQUIRES: value > 0
    // MODIFIES: this
    // EFFECTS: calculates protein intake of the food eaten and adds it to the total protein intake of the day
    // returns total protein intake of the day
    public double calculateProtein() {
        double totalProtein = 0;
        for (Map.Entry<Food, Double> item : foodEaten.entrySet()) {
            System.out.println("Food: " + item.getKey().getName() + ", Quantity: " + item.getValue());
            totalProtein += item.getKey().getProteinCountPerHundredGrams() * item.getValue();
        }
        return totalProtein;
    }

    // REQUIRES: proteinGoal > 0
    // MODIFIES: this
    // EFFECTS: outputs protein progress (in %) achieved based on proteinGoal
    public double getProgressPourcentage() {
        double progress = (calculateProtein() / getProteinGoal()) * 100;
        return progress;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProteinGoal() {
        return proteinGoal;
    }

    public void setProteinGoal(int proteinGoal) {
        this.proteinGoal = proteinGoal;
    }
}
