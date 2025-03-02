package persistence;

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
            assertEquals(2, foods.size());
            checkFood("Chicken", 31, foods.get(0));
            checkFood("Salmon", 22, foods.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
