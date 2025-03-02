package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import model.Food;

public class JsonTest {
    protected void checkFood(String name, double protein, Food food) {
        assertEquals(name, food.getName());
        assertTrue(protein == food.getProteinCountPerHundredGrams());
    }
}
