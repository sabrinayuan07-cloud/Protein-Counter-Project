package model;


import org.json.JSONObject;

import persistence.Writable;

// Represents a food item that has a name and the number of protein per 100grams
public class Food implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("proteinCountPerHundredGrams", proteinCountPerHundredGrams);
        return json;
    }
}
