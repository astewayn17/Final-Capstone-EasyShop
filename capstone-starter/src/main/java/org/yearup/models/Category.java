package org.yearup.models;

public class Category {

    // Properties that define a category
    private int categoryId;
    private String name;
    private String description;

    // Empty constructor
    public Category() {}

    // Constructor
    public Category(int categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    // Setters and getters for the properties
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}