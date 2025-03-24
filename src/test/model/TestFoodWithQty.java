package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestFoodWithQty {
    private FoodWithQty foodWithQty;
    private Food testFoodBacon;
    private Food testFoodShrimp;

    @Before
    public void runBefore() {
        testFoodBacon = new Food("Bacon", 37);
        testFoodShrimp = new Food("Shrimp", 20);
        foodWithQty = new FoodWithQty(testFoodBacon, 200.0);
    }

    @Test
    public void testConstructor() {
        assertEquals(testFoodBacon, foodWithQty.getFood());
        assertEquals(200.0, foodWithQty.getQty(), 0.01);
    }

    @Test
    public void testSetFood() {
        foodWithQty.setFood(testFoodShrimp);
        assertEquals(testFoodShrimp, foodWithQty.getFood());
    }

    @Test
    public void testSetQty() {
        foodWithQty.setQty(200.0);
        assertEquals(200.0, foodWithQty.getQty(), 0.01);
    }


    @Test
    public void testToString() {
        assertEquals(foodWithQty.toString(), "- 200.0 grams of Bacon");
    }

}
