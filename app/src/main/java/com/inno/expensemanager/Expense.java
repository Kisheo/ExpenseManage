package com.inno.expensemanager;

public class Expense {
    private int id;
    private String description;
    private String category;
    private double amount;

    public Expense(int id, String description, String category, double amount) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    // Getters
    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
}
