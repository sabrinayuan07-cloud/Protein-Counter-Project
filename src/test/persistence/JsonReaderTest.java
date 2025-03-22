package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

import model.Food;
import model.MealPlan;

// SOURCE: FROM JSON LIBRAIRIES SAMPLE APPLICATION DEMO

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MealPlan mp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyMealPlan() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMealPlan.json");
        try {
            MealPlan mp = reader.read();
            assertEquals("My meal plan", mp.getName());
            assertTrue(120 == mp.getProteinGoal());
            Food chicken = new Food("Chicken", 31);
            assertTrue(0 == mp.getFoodQuantity(chicken));
            assertTrue(0 == mp.getAllFoodEaten().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralMealPlan() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMealPlan.json");
        try {
            MealPlan mp = reader.read();
            assertEquals("My meal plan", mp.getName());
            assertTrue(130 == mp.getProteinGoal());
            ArrayList<Food> foods = mp.getAllFoodEaten();
            assertEquals(2, foods.size());
            checkFood("Chicken", 31, foods.get(0));
            checkFood("Salmon", 22, foods.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
