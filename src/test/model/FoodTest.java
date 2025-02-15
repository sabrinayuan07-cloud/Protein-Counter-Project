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
    void testConstructor() {
        assertEquals("Chicken", testFoodBacon.getName());
        assertEquals(37, testFoodBacon.getProteinCountPerHundredGrams());
    }

    @Test
    void testGetName() {

    }

    @Test
    void testGetProteinCountPerHundredGrams() {

    }

    @Test
    void testSetName() {

    }

    @Test
    void testSetProteinCountPerHundredGrams() {
        
    }

}
