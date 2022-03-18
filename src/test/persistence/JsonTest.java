package persistence;

import model.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkRecipe(String name, String ingredientList, String stepList, Recipe recipe) {

        assertEquals(name, recipe.getRecipeTitle());
        assertEquals(ingredientList, recipe.getIngredients());
        assertEquals(stepList, recipe.getSteps());
        }
}
