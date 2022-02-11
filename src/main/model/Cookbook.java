package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Represents a cookbook with name and recipes

public class Cookbook {
    private ArrayList<Recipe> recipes;
    private String name;

    // REQUIRES: string must not be empty string, there may only be one Cookbook at one given time
    // EFFECTS: initializes cookbook with a name, and no recipes
    public Cookbook(String cookbookName) {
        this.name = cookbookName;
        this.recipes = new ArrayList<Recipe>();
    }

    // REQUIRES: the recipe must have a unique name, and string must not be empty
    // MODIFIES: this
    // EFFECTS: adds a recipe to the cookbook
    public void addRecipe(Recipe r) {
        recipes.add(r);
        System.out.println(r + " has been added!");
    }

    // REQUIRES: recipe must already exist in the cookbook
    // MODIFIES: this
    // EFFECTS: removes a recipe from the cookbook
    public void removeRecipe(Recipe r) {
        recipes.remove(r);
        System.out.println(r + " has been removed :(");
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    // EFFECTS: return name of cookbook
    public String getName() {
        return name;
    }

}
