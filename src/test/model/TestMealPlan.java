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

    @Before
    public void runBefore() {
        mealPlanTest = new MealPlan("Savannah", 120);
        testFoodSalmon = new Food("Salmon", 22);
        testFoodTofu = new Food("Tofu", 8);
    }

    @Test
    public void testConstructor() {
        MealPlan mealPlan1 = new MealPlan("Julia",90);
        assertEquals(mealPlan1.getName(), "Julia");
        assertEquals(mealPlan1.getProteinGoal(), 90);
    }

    @Test
    public void testAddFood() {
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 0);
        mealPlanTest.addFood(testFoodSalmon, 2);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 2);

        assertEquals(mealPlanTest.getFoodQuantity(testFoodTofu), 0);
        mealPlanTest.addFood(testFoodTofu, 5);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodTofu), 5);

        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 2);
        mealPlanTest.addFood(testFoodSalmon, 7);
        assertEquals(mealPlanTest.getFoodQuantity(testFoodSalmon), 9);
    }

    @Test
    public void testCalculateProtein() {
        mealPlanTest.addFood(testFoodSalmon, 5);
        mealPlanTest.addFood(testFoodTofu, 2);
        double proteinCount = (testFoodSalmon.getProteinCountPerHundredGrams() * 5) + (testFoodTofu.getProteinCountPerHundredGrams() * 2);
        assertEquals(mealPlanTest.calculateProtein(), proteinCount);
    }

    @Test
    public void testGetProgressPourcentage() {
        mealPlanTest.addFood(testFoodSalmon, 5);
        double proteinCount = (testFoodSalmon.getProteinCountPerHundredGrams() * 5);
        double progress = (proteinCount / mealPlanTest.getProteinGoal()) * 100;
        assertEquals(mealPlanTest.getProgressPourcentage(), progress);

        mealPlanTest.addFood(testFoodTofu, 2);
        double proteinCount2 = proteinCount + (testFoodTofu.getProteinCountPerHundredGrams() * 2);
        double progress2 = (proteinCount2 / mealPlanTest.getProteinGoal()) * 100;
        assertEquals(mealPlanTest.getProgressPourcentage(), progress2);
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
