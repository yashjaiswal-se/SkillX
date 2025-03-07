package com.example.skillx;


public class Skill {
    private String title;
    private String category;
    private int cost;

    // Constructor
    public Skill(String title, String category, int cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getCost() {
        return cost;
    }
}
