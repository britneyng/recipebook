package model;

import java.util.ArrayList;

// Represents a list of the ingredients that are required for a recipe
public class IngredientList {

    private ArrayList<Ingredient> ingredientList;

    // EFFECTS: initializes a new list of ingredients to be used in a recipe
    public IngredientList() {
        this.ingredientList = new ArrayList<Ingredient>();
    }

    // REQUIRES: ingredient must already be instantiated
    // MODIFIES: this
    // EFFECTS: adds an ingredient to the end of the list of recipeIngredients
    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);

    }

    // REQUIRES: ingredient to be removed must already exist in the list
    // MODIFIES: this
    // EFFECTS: removes an ingredient from the list of recipeIngredients
    public void removeIngredient(Ingredient ingredient) {
        ingredientList.remove(ingredient);
    }

    // EFFECTS: returns true if the ingredientList contains a given ingredient, false otherwise
    public Boolean hasIngredient(Ingredient ingredient) {
        if (ingredientList.contains(ingredient)) {
            return true;
        }
        return false;
    }


    // REQUIRES: ingredientList must have at least one item within it
    // EFFECTS: returns the list of ingredients in the form of a string
    public String getIngredientList() {
        String groceryList = "";

        for (int i = 0; i <= (ingredientList.size() - 1); i++) {

            int num = (i + 1); // this number tells us about the location of the ingredient in the list

            groceryList = groceryList.concat(" " + num + "." + " "
                    + ingredientList.get(i).name + ","
                    + " " + ingredientList.get(i).amount + " " + ingredientList.get(i).unit);
        }

        return groceryList;
    }

}
