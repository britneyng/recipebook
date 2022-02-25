package model;

import java.util.ArrayList;

// Represents a cookbook with name and recipes

public class Cookbook {
    private ArrayList<Recipe> recipes;

    // REQUIRES: string must not be empty string, only one Cookbook can exist at a time
    // EFFECTS: initializes cookbook with a name, and no recipes
    public Cookbook() {
        this.recipes = new ArrayList<Recipe>();
    }

    // REQUIRES: the recipe must have a unique name, and string must not be empty
    // MODIFIES: this
    // EFFECTS: adds a recipe to the cookbook
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    // REQUIRES: recipe must already exist in the cookbook
    // MODIFIES: this
    // EFFECTS: removes a recipe from the cookbook
    public void removeRecipe(Recipe recipe) {
        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            if (r.getRecipeTitle() == recipe.getRecipeTitle()) {
                recipes.remove(recipe);
            }
        }
    }

    // REQUIRES: Cookbook must contain at least one recipe
    // EFFECTS: goes through a list of recipes and returns it if found
    public Recipe findRecipe(String recipe) {
        for (Recipe r : recipes) {
            if (r.getRecipeTitle().equals(recipe)) {
                return r;
            }
        }
        return null;
    }

    // EFFECTS: returns a list of recipes that have been added to the cookbook
    public ArrayList<Recipe> getRecipeList() {
        return recipes;
    }

    // method for testing
    // EFFECTS: return size of cookbook
    public int length() {
        return recipes.size();
    }

}
