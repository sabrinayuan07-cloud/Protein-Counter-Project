package model;

public class User {
    private String name;
    private double proteinGoal;

    public User(String name, double proteinGoal) {
        this.name = name;
        this.proteinGoal = proteinGoal;
    }

    public String getName() {
        return null;
        // stub
    }

    public double getProteinGoal() {
        return 0;
        // stub
    }

    public void setName(String newName) {
        this.name = "";
        // stub
    }

    public void setProteinGoal(double newProteinGoal) {
        this.proteinGoal = 0;
        // stub
    }

    // REQUIRES: proteinGoal > 0
    // MODIFIES: this
    // EFFECTS: outputs protein progress (in %) achieved based on proteinGoal
    public int progress() {
        return 0;
        // stub
    }
}

