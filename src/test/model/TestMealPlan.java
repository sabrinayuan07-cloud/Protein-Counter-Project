package model;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestMealPlan {
    private MealPlan mealPlanTest;
    private Food testFoodSalmon;
    private Food testFoodTofu;
    private Food testFoodChicken;

    @Before
    public void runBefore() {
        mealPlanTest = new MealPlan("Savannah", 120);
        testFoodSalmon = new Food("Salmon", 22.5);
        testFoodTofu = new Food("Tofu", 8.3);
        testFoodChicken = new Food("Chicken", 30.1);
    }

    @Test
    public void testConstructor() {
        MealPlan mealPlan1 = new MealPlan("Julia",90);
        assertEquals(mealPlan1.getName(), "Julia");
        assertEquals(mealPlan1.getProteinGoal(), 90);
    }

    @Test
    public void testGetFoodQuantity() {
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 0);
        mealPlanTest.addFood(testFoodSalmon, 100);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 100);
    }

    @Test
    public void testGetAllFoodEaten() {
        assertEquals(mealPlanTest.getAllFoodEaten().size(), 0);
        assertFalse(mealPlanTest.getAllFoodEaten().contains(testFoodTofu));
        assertFalse(mealPlanTest.getAllFoodEaten().contains(testFoodSalmon));
        assertFalse(mealPlanTest.getAllFoodEaten().contains(testFoodChicken));
        mealPlanTest.addFood(testFoodTofu, 200);
        mealPlanTest.addFood(testFoodSalmon, 100);
        mealPlanTest.addFood(testFoodChicken, 500);
        assertEquals(mealPlanTest.getAllFoodEaten().size(), 3);
        assertTrue(mealPlanTest.getAllFoodEaten().contains(testFoodTofu));
        assertTrue(mealPlanTest.getAllFoodEaten().contains(testFoodSalmon));
        assertTrue(mealPlanTest.getAllFoodEaten().contains(testFoodChicken));
    }

    @Test
    public void testAddFood() {
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 0);
        mealPlanTest.addFood(testFoodSalmon, 100);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 100);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodTofu), 0);
        mealPlanTest.addFood(testFoodTofu, 0);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodTofu), 0);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 100);
        mealPlanTest.addFood(testFoodSalmon, 700);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 800);
    }

    @Test
    public void testCalculateProtein() {
        mealPlanTest.addFood(testFoodSalmon, 50);
        mealPlanTest.addFood(testFoodTofu, 20);
        double proteinCount = ((testFoodSalmon.getProteinCountPerHundredGrams() / 100) * 50) + ((testFoodTofu.getProteinCountPerHundredGrams()/100) * 20);
        assertEquals(mealPlanTest.calculateProtein(), proteinCount);
    }

    @Test
    public void testGetProgressPourcentage() {
        mealPlanTest.addFood(testFoodSalmon, 500);
        double proteinCount = ((testFoodSalmon.getProteinCountPerHundredGrams()/100) * 500);
        int progress = (int) Math.round((proteinCount / mealPlanTest.getProteinGoal()) * 100);
        assertEquals(mealPlanTest.getProgressPourcentage(), progress);
        mealPlanTest.addFood(testFoodTofu, 2);
        double proteinCount2 = proteinCount + ((testFoodTofu.getProteinCountPerHundredGrams() / 100) * 2);
        int progress2 = (int) Math.round((proteinCount2 / mealPlanTest.getProteinGoal()) * 100);
        assertEquals(mealPlanTest.getProgressPourcentage(), progress2);
        mealPlanTest.addFood(testFoodChicken, 100);
        assertEquals(mealPlanTest.getProgressPourcentage(), 100);
    }

    @Test
    public void testSetName() {
        assertEquals(mealPlanTest.getName(), "Savannah");
        mealPlanTest.setName("Lola");
        assertEquals(mealPlanTest.getName(), "Lola");
    }

    @Test
    public void testGetProteinGoal() {
        assertEquals(mealPlanTest.getProteinGoal(), 120);
    }

    @Test
    public void testSetProteinGoal() {
        assertEquals(mealPlanTest.getProteinGoal(), 120);
        mealPlanTest.setProteinGoal(140);
        assertEquals(mealPlanTest.getProteinGoal(), 140);
    }
}
