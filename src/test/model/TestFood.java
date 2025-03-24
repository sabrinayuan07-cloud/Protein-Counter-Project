package model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.Before;

public class TestFood {
    private Food testFoodBacon;
    private Food testFoodShrimp;

    @Before
    public void runBefore() {
        testFoodBacon = new Food("Bacon", 37);
        testFoodShrimp = new Food("Shrimp", 20);
    }

    @Test
    public void testConstructor() {
        Food testFood1 = new Food("1", 10);
        assertEquals(testFood1.getName(), "1");
        assertEquals(testFood1.getProteinCountPerHundredGrams(), 10);
    }

    @Test
    public void testGetName() {
        assertEquals(testFoodBacon.getName(), "Bacon");
    }

    @Test
    public void testGetProteinCountPerHundredGrams() {
        assertEquals(testFoodBacon.getProteinCountPerHundredGrams(), 37);
    }

    @Test
    public void testSetName() {
        assertEquals(testFoodShrimp.getName(), "Shrimp");
        testFoodShrimp.setName("Chicken");
        assertEquals(testFoodShrimp.getName(), "Chicken");
    }

    @Test
    public void testSetProteinCountPerHundredGrams() {
        assertEquals(testFoodBacon.getProteinCountPerHundredGrams(), 37);
        testFoodBacon.setProteinCountPerHundredGrams(30);
        assertEquals(testFoodBacon.getProteinCountPerHundredGrams(), 30);
    }

    @Test
    public void testToJson() {
        JSONObject json = testFoodBacon.toJson();
        assertEquals("Bacon", json.get("name"));
        assertEquals(37.0, json.get("proteinCountPerHundredGrams"));
    }

    @Test
    public void testToString() {
        assertEquals(testFoodBacon.toString(), "Bacon has 37.0g of protein for every 100g");
        // @Override
        // public String toString() {
        //     return name + " has " + proteinCountPerHundredGrams + "g of protein for every 100g";
        // }
    }
}
