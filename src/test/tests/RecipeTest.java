package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    private Recipe testRecipe;
    private IngredientList testIngredientList;
    private StepList emptyStepList;
    private Ingredient testIngredient;
    private Step testStepA;
    private Step testStepB;
    private StepList testStepList;

    @BeforeEach
    void runBefore() {
        testIngredientList = new IngredientList();
        testIngredient = new Ingredient("Egg", 1, "whole");
        testStepA = new Step(1, "Add oil to pan");
        testStepB = new Step(2, "Crack egg");
        testStepList = new StepList();
        emptyStepList = new StepList();
        testIngredientList.addIngredient(testIngredient);
        testStepList.addStep(testStepA);
        testStepList.addStep(testStepB);
        testRecipe = new Recipe("Fried Egg", testIngredientList, testStepList);
    }

    @Test
    void getRecipeTitleTest(){
        assertEquals("Fried Egg", testRecipe.getRecipeTitle());
    }

    @Test
    void getRecipeIngredientsTest() {
        assertFalse(testIngredientList == null);
        assertEquals("Egg, 1.0 whole" + "\n" ,testRecipe.getIngredients());
    }

    @Test
    void getCurrentStepTest() {
        assertEquals("1. Add oil to pan" + "\n" + "2. Crack egg" + "\n", testRecipe.getSteps());
    }

    @Test
    void toStringTest() {
        assertEquals("Fried Egg", testRecipe.toString());
    }
}

