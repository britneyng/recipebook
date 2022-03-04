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
        if (ingredients != null) {
            return ingredients.getIngredientList();
        }
        return null;
    }

    public String getSteps() {
        if (steps != null) {
            return steps.getSteps();
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", recipeTitle);
        if (ingredients != null) {
            json.put("ingredients", ingredients.toJson());
        }
        if (steps != null) {
            json.put("steps", steps.toJson());
        }
        return json;
    }
}
