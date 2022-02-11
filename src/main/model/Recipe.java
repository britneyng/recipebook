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

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getIngredients() {
        return ingredients.getIngredientList();
    }

    public Step getCurrentStep(int num) {
        return steps.displayStep(num);
    }
}
