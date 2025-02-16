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
        return null;
        // stub
    }
    
    public double getProteinCountPerHundredGrams() {
        return 0;
        // stub
    }

    public void setName(String name) {
        this.name = "";
        // stub
    }

    public void setProteinCountPerHundredGrams(double protein) {
        this.proteinCountPerHundredGrams = 0;
        // stub
    }

    
}
