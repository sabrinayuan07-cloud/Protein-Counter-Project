package model;

public class Food {
    private String name;
    private double proteinCountPerHundredGrams;

    public Food(String name, double proteinCountPerHundredGrams) {
        this.name = name;
        this.proteinCountPerHundredGrams = proteinCountPerHundredGrams;
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
