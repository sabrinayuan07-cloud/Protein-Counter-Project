package model;

// Represents a food item that has a name and the number of protein per 100grams
public class Food {
    private String name; // name of food item
    private double proteinCountPerHundredGrams; // protein count per 100grams

    public Food(String name, double proteinCountPerHundredGrams) {
        this.name = name;
        this.proteinCountPerHundredGrams = proteinCountPerHundredGrams; // > 0
    }

    public String getName() {
        return this.name;
    }
    
    public double getProteinCountPerHundredGrams() {
        return this.proteinCountPerHundredGrams;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProteinCountPerHundredGrams(double protein) {
        this.proteinCountPerHundredGrams = protein;
    }

}
