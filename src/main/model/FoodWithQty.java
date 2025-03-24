package model;

public class FoodWithQty {
    private Food food;
    private Double quantity;
    
    public FoodWithQty(Food food, Double quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setQty(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQty() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return  "- " + quantity + " grams of " + food.getName();
    }
}
