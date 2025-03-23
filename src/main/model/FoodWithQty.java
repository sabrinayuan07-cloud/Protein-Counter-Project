package model;

public class FoodWithQty {
    private Food food;
    private Double quantity;
    
    public FoodWithQty(Food food, Double quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return  "- " + quantity + " grams of " + food.getName();
    }
}
