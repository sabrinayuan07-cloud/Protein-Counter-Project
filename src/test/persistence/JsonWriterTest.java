package persistence;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Food;
import model.MealPlan;

// SOURCE: FROM JSON LIBRAIRIES SAMPLE APPLICATION DEMO

public class JsonWriterTest extends JsonTest {
    @Test
    public void testWriterInvalidFile() {
        try {
            MealPlan mp = new MealPlan("My meal plan", 120);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyMealPlan() {
        try {
            MealPlan mp = new MealPlan("My meal plan", 120);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMealPlan.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMealPlan.json");
            mp = reader.read();
            assertEquals("My meal plan", mp.getName());
            assertEquals(120, mp.getProteinGoal());
            assertEquals(0, mp.getAllFoodEaten().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralMealPlan() {
        try {
            MealPlan mp = new MealPlan("My meal plan", 120);
            Food chicken = new Food("Chicken", 31);
            mp.addFood(chicken, 200);
            Food salmon = new Food("Salmon", 22);
            mp.addFood(salmon, 150);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMealPlan.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMealPlan.json");
            mp = reader.read();
            assertEquals("My meal plan", mp.getName());
            ArrayList<Food> foods = mp.getAllFoodEaten();

            boolean foundChicken = false;
            boolean foundSalmon = false;
            for (Food f : foods) {
                System.out.println(f.getName());
                if (f.getName().equalsIgnoreCase(chicken.getName())) {
                    if (Double.compare(f.getProteinCountPerHundredGrams(),
                            chicken.getProteinCountPerHundredGrams()) == 0) {
                        foundChicken = true;
                    }
                } else if (f.getName().equalsIgnoreCase(salmon.getName())) {
                    if (Double.compare(f.getProteinCountPerHundredGrams(),
                            salmon.getProteinCountPerHundredGrams()) == 0) {
                        foundSalmon = true;
                    }
                }
            }
            assertTrue(foundChicken);
            assertTrue(foundSalmon);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
