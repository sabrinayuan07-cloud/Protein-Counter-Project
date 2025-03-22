package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Food;
import model.MealPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

// Protein counter application
public class ProteinCounterApp {
    private MealPlan mealPlan;
    private ArrayList<Food> foodToChooseFrom;
    private Scanner input;
    private static final String JSON_STORE = "./data/mealplan.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the protein counter application
    public ProteinCounterApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        mealPlan = new MealPlan("Sabrina's workroom", 130);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runProteinCounter();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runProteinCounter() {
        // init();
        String loadFile;
        System.out.println("Welcome to your personalized protein counter application " + mealPlan.getName() + "!");
        while (true) {
            System.out.println("If you want to load a meal plan from file, press \"y\", or else press \"n\": ");
            loadFile = input.next().trim().toLowerCase();
            if (loadFile.equals("y")) {
                loadMealPlan();
                init();
                displayMenu();
                break;
            } else if (loadFile.equals("n")) {
                init();
                displayMenu();
                break;
            } else {
                System.out.println("Invalid input, please try again: ");
            }
        }
    }

    // REQUIRES: proteinGoal > 0
    // MODIFIES: this
    // EFFECTS: initializes protein couter application
    // and asks for user name and protein goal
    private void init() {
        input = new Scanner(System.in);
        initializeFoodMenu();
        System.out.println("Enter your name: ");
        String name = input.next().trim();
        double proteinGoal = getProteinGoal();
        mealPlan = new MealPlan(name, proteinGoal);
    }

    private double getProteinGoal() {
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
        return proteinGoal;
    }

    // EFFECTS: initializes the food menu available for users
    private void initializeFoodMenu() {
        foodToChooseFrom = new ArrayList<Food>();
        foodToChooseFrom.add(new Food("Chicken", 31));
        foodToChooseFrom.add(new Food("Eggs", 13));
        foodToChooseFrom.add(new Food("Milk", 3.3));
        foodToChooseFrom.add(new Food("Salmon", 22));
        foodToChooseFrom.add(new Food("Beef", 26));
        foodToChooseFrom.add(new Food("Shrimp", 20));
        foodToChooseFrom.add(new Food("Tofu", 8));
        foodToChooseFrom.add(new Food("Oats", 11));
        foodToChooseFrom.add(new Food("Pasta", 5));
        foodToChooseFrom.add(new Food("Broccoli", 2.8));
        foodToChooseFrom.add(new Food("Avocado", 2));
        foodToChooseFrom.add(new Food("Banana", 1.1));
        foodToChooseFrom.add(new Food("Bacon", 37));
    }

    // MODIFIES: this
    // EFFECTS: displays menu of food options to user and allows user to add food to
    // meal plan
    private void displayMenu() {
        System.out.println("Here is the food selection: ");
        for (Food food : foodToChooseFrom) {
            String foodName = food.getName();
            double proteinCount = food.getProteinCountPerHundredGrams();
            System.out.println(foodName + " has " + proteinCount + "g of protein for every 100g");
        }
        selectFood();
    }

    // EFFECTS: calls printSummary with reset boolean as parameter
    private void selectFood() {
        String foodSelected = helperSelectFood();
        if (foodSelected.equals("r")) {
            printSummary(true);
            runProteinCounter();
        } else {
            printSummary(false);
        }
    }

    // EFFECTS: calls different methods depending on user input
    private String helperSelectFood() {
        String foodSelected;
        while (true) {
            System.out.println(
                    "Enter the food you want to add to your meal plan, or press \"q\" when done,"
                            + " press \"p\" to view protein progress and \"r\" when you want "
                            + "to start a new day: ");
            foodSelected = input.next().trim().toLowerCase();
            if (foodSelected.equals("p")) {
                proteinProgress();
            } else if (getFoodFromName(foodSelected) != null) {
                System.out.println("Enter the amount of food in grams: ");
                double quantity = input.nextDouble();
                Food foodToAdd = getFoodFromName(foodSelected);
                mealPlan.addFood(foodToAdd, quantity);
                int proteinCount = (int) Math.round(mealPlan.calculateProtein());
                System.out.println("You have now eaten " + proteinCount + "g of protein!");
            } else if (foodSelected.equals("q") || foodSelected.equals("r")) {
                break;
            } else {
                System.out.println("Food is not found, please try again");
            }
        }
        return foodSelected;
    }

    private void proteinProgress() {
        int progress = mealPlan.getProgressPourcentage();
        System.out.println("You have now completed " + progress + "% of your protein goal");
        ArrayList<Food> foodEaten = mealPlan.getAllFoodEaten();

        if (foodEaten.isEmpty()) {
            System.out.println("You have not eaten anything yet.");
        } else {
            System.out.println("Here is what you ate so far: ");
            for (Food food : foodEaten) {
                double quantity = mealPlan.getFoodQuantity(food);
                System.out.println("- " + quantity + " grams of " + food.getName());
            }
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
            System.out.println("You have not eaten anything");
            wantToSaveMealPlan();
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
            wantToSaveMealPlan();
            System.out.println("Your protein intake has been reset for a new day :)");
        } else {
            wantToSaveMealPlan();
            System.out.println("Have a nice day :)");
        }
    }

    // MODIFIES:
    // EFFECTS:
    private void wantToSaveMealPlan() {
        String save;
        System.out.println("Do you want to save your meal plan to file? Enter \"y\" or \"n\": ");
        save = input.next().trim().toLowerCase();
        while (true) {
            if (save.equals("y")) {
                saveMealPlan();
                break;
            } else if (save.equals("n")) {
                System.out.println("You have chosen not to save your meal plan");
                break;
            } else {
                System.out.println("Invalid input, please try again");
            }
        }
    }

    // EFFECTS: saves mealplan to JSON file
    public void saveMealPlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(mealPlan);
            jsonWriter.close();
            System.out.println("Saved " + mealPlan.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads mealplan from file if it exists
    public void loadMealPlan() {
        try {
            mealPlan = jsonReader.read();
            System.out.println("Loaded " + mealPlan.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}