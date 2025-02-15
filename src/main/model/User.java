package model;

public class User {
    private String name;
    private double proteinGoal;

    public User(String name, double proteinGoal) {
        this.name = name;
        this.proteinGoal = proteinGoal;
    }

    public String getName() {
        return this.name;
    }

    public double getProteinGoal() {
        return this.proteinGoal;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setProteinGoal(double newProteinGoal) {
        this.proteinGoal = newProteinGoal;
    }

    // REQUIRES: proteinGoal > 0
    // MODIFIES:
    // EFFECTS: 
    public void progress() {

    }
}

