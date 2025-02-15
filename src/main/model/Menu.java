package model;

import java.util.ArrayList;

// Represents the food items available for user to choose from
public class Menu {
    private ArrayList<Food> itemList;

    public Menu() {
        itemList = new ArrayList<Food>();
        itemList.add(new Food ("Chicken breast", 31));
        itemList.add(new Food ("Eggs", 13));
        itemList.add(new Food ("Milk", 3.3));
        itemList.add(new Food ("Greek yogurt", 10));
        itemList.add(new Food ("Salmon", 22));
        itemList.add(new Food ("Ground beef", 26));
        itemList.add(new Food ("Shrimp", 20));
        itemList.add(new Food ("Tofu", 8));
        itemList.add(new Food ("Oats", 11));
        itemList.add(new Food ("Pasta", 5));
        itemList.add(new Food ("Broccoli", 2.8));
        itemList.add(new Food ("Avocado", 2));
        itemList.add(new Food ("Banana", 1.1));
        itemList.add(new Food ("Tomatoes", 0.9));
        itemList.add(new Food ("Bacon", 37));
        itemList.add(new Food ("Cottage Cheese", 11));
        
    }   
}
