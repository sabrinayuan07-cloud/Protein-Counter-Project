package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Food;
import model.MealPlan;
import persistence.JsonReader;

// Protein counter application
public class ProteinCounterApp {
    private MealPlan mealPlan;
    private ArrayList<Food> foodToChooseFrom;
    private Scanner input;

    // EFFECTS: runs the protein counter application
    public ProteinCounterApp() {
        runProteinCounter();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runProteinCounter() {
        init();
        System.out.println("Welcome to your personalized protein counter application " + mealPlan.getName() + "!");
        displayMenu();
    }

    // REQUIRES: proteinGoal > 0
    // MODIFIES: this
    // EFFECTS: initializes protein couter application
    // and asks for user name and protein goal
    private void init() {
        input = new Scanner(System.in);

        foodToChooseFrom = new ArrayList<Food>();
        // foodToChooseFrom.add(new Food("Chicken", 31));
        // foodToChooseFrom.add(new Food("Eggs", 13));
        // foodToChooseFrom.add(new Food("Milk", 3.3));
        // foodToChooseFrom.add(new Food("Salmon", 22));
        // foodToChooseFrom.add(new Food("Beef", 26));
        // foodToChooseFrom.add(new Food("Shrimp", 20));
        // foodToChooseFrom.add(new Food("Tofu", 8));
        // foodToChooseFrom.add(new Food("Oats", 11));
        // foodToChooseFrom.add(new Food("Pasta", 5));
        // foodToChooseFrom.add(new Food("Broccoli", 2.8));
        // foodToChooseFrom.add(new Food("Avocado", 2));
        // foodToChooseFrom.add(new Food("Banana", 1.1));
        // foodToChooseFrom.add(new Food("Bacon", 37));

        JsonReader reader = new JsonReader("./data/foodMenu.json");
        foodToChooseFrom = reader.readListFood();
        //??????

        System.out.println("Enter your name: ");
        String name = input.next().trim();

        double proteinGoal = 0;
        boolean proteinGoalEntered = false;

        while (!proteinGoalEntered) {
            System.out.println("What is your daily protein goal in grams: ");
                if (input.hasNextDouble()) {
                    proteinGoal = input.nextDouble();
                    if (proteinGoal <= 0) {
                        System.out.println("Protein goal must be greater than 0.");
                    } else {
                        proteinGoalEntered = true;
                    }
                } else {
                    System.out.println("Protein goal must be a valid number greater than 0.");
                    input.next(); // Clear invalid input
                }
        }
        mealPlan = new MealPlan(name, proteinGoal);
    }

    // MODIFIES: this
    // EFFECTS: displays menu of food options to user and allows user to add food to meal plan
    private void displayMenu() {
        System.out.println("Here is the food selection: ");
        for (Food food : foodToChooseFrom) {
            String foodName = food.getName();
            double proteinCount = food.getProteinCountPerHundredGrams();
            System.out.println(foodName + " has " + proteinCount + "g of protein for every 100g");
        }

        String foodSelected;
        while (true) {
            System.out.println("Enter the food you want to add to your meal plan, or press \"q\" when done, and \"r\" when you want to start a new day: ");
            foodSelected = input.next().trim().toLowerCase();

            if (getFoodFromName(foodSelected) != null) {
                System.out.println("Enter the amount of food in grams: ");
                double quantity = input.nextDouble();
                Food foodToAdd = getFoodFromName(foodSelected);
                mealPlan.addFood(foodToAdd, quantity);

                int proteinCount = (int) Math.round(mealPlan.calculateProtein());
                System.out.println("You have now eaten " + proteinCount + "g of protein!");

                int progress = mealPlan.getProgressPourcentage();
                System.out.println("You have now completed " + progress + "% of your protein goal");
            } else if (foodSelected.equals("q") || foodSelected.equals("r")) {
                break;
            } else {
                System.out.println("Food is not found, please try again");
            }
        }

        if (foodSelected.equals("r")) {
            printSummary(true);
            runProteinCounter();
        } else {
            printSummary(false);
        }
    }

    // EFFECTS: returns Food object from foodName, if cannot be found, return null
    private Food getFoodFromName(String foodName) {
        for (Food food : foodToChooseFrom) {
            if (food.getName().toLowerCase().equals(foodName)) {
                return food;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: print summary of food eaten, total protein and progress and
    // clears list of foods eaten
    private void printSummary(boolean isReset) {
        System.out.println("Here is a summary of what you ate today");
        ArrayList<Food> foodEaten = mealPlan.getAllFoodEaten();

        if (foodEaten.isEmpty()) {
            System.out.println("You have not eaten anything today.");
        } else {
            System.out.println("You have eaten: ");
            for (Food food : foodEaten) {
                double quantity = mealPlan.getFoodQuantity(food);
                System.out.println("- " + quantity + " grams of " + food.getName());
            }
        }
        System.out.println(
                "Your total protein intake of the day is " + (int) Math.round(mealPlan.calculateProtein()) + "g.");
        System.out.println("You have reached " + mealPlan.getProgressPourcentage() + "% of your protein goal.");

        foodEaten.clear();
        if (isReset) {
            System.out.println("Your protein intake has been reset for a new day :)");
        } else {
            System.out.println("Have a nice day :)");
        }
    }
}
