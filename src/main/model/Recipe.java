package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a recipe composed of a title, IngredientList, and StepList

public class Recipe implements Writable {
    private String recipeTitle;
    private IngredientList ingredients;
    private StepList steps;

    // EFFECTS: initializes recipe with a name, ingredients and steps
    public Recipe(String recipeTitle, IngredientList ingredients, StepList steps) {
        this.recipeTitle = recipeTitle;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    // getters

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getIngredients() {
        return ingredients.getIngredientList();
    }

    public String getSteps() {
        return steps.getSteps();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", recipeTitle);
        json.put("ingredients", ingredients.toJson());
        json.put("steps", steps.toJson());
        return json;
    }

    @Override
    public String toString() {
        return recipeTitle;
    }
}
