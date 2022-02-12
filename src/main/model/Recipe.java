package model;


// Represents a recipe composed of a title, IngredientList, and StepList
public class Recipe {
    private String recipeTitle;
    private IngredientList ingredients;
    private StepList steps;

    // EFFECTS: initializes recipe with a name, a time in minutes, and steps
    public Recipe(String recipeTitle, IngredientList ingredients, StepList steps) {
        this.recipeTitle = recipeTitle;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    // EFFECTS: returns the title of the recipe
    public String getRecipeTitle() {
        return recipeTitle;
    }

    // EFFECTS: returns the ingredients in the list of required ingredients for the recipe
    public String getIngredients() {
        return ingredients.getIngredientList();
    }

    // EFFECTS: return the instructions for the current step
    public Step getCurrentStep(int num) {
        return steps.getStep(num);
    }
}
