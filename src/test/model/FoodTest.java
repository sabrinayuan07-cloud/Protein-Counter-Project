package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food testFood1;

    @BeforeEach
    void runBefore() {
        testFood1 = new Food("Bacon", 37);
    }

    @Test
    void testConstructor() {
        assertEquals("Chicken", testFood1.getName());
        assertEquals(37, testFood1.getProteinCountPerHundredGrams());
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
