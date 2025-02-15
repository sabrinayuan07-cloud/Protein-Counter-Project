package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food testFoodBacon;
    private Food testFoodShrimp;

    @BeforeEach
    void runBefore() {
        testFoodBacon = new Food("Bacon", 37);
        testFoodShrimp = new Food("Shrimp", 20);
    }

    @Test
    public void testConstructor() {
        assertTrue(testFoodBacon != null);
        assertTrue(testFoodShrimp != null);
    }

    @Test
    public void testGetName() {
        assertTrue(testFoodBacon.getName() != null);
        assertEquals(testFoodBacon.getName(), "Bacon");
        assertTrue(testFoodBacon.getName() != null);
    }

    @Test
    public void testGetProteinCountPerHundredGrams() {
        assertTrue(testFoodBacon.getName() != null);
        assertTrue(testFoodBacon.getProteinCountPerHundredGrams() != 0);
        assertEquals(testFoodBacon.getProteinCountPerHundredGrams(), 37);
        assertTrue(testFoodBacon.getName() != null);
    }

    @Test
    public void testSetName() {
        assertTrue(testFoodShrimp != null);
        assertEquals(testFoodShrimp.getName(), "Shrimp");
        assertEquals(testFoodShrimp.getProteinCountPerHundredGrams(), 20);
        testFoodShrimp.setName("Chicken");
        assertTrue(testFoodShrimp != null);
        assertEquals(testFoodShrimp.getName(), "Chicken");
        assertEquals(testFoodShrimp.getProteinCountPerHundredGrams(), 20);
    }

    @Test
    public void testSetProteinCountPerHundredGrams() {
        assertTrue(testFoodBacon != null);
        assertEquals(testFoodBacon.getProteinCountPerHundredGrams(), 37);
        testFoodBacon.setProteinCountPerHundredGrams(30);
        assertTrue(testFoodBacon != null);
        assertEquals(testFoodBacon.getProteinCountPerHundredGrams(), 30);
    }
}
