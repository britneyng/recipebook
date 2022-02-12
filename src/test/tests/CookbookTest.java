package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookbookTest {

    private Cookbook testCookbook;
    private Recipe testRecipe;
    private IngredientList testIngredientList;
    private Ingredient testIngredient;
    private Step testStepA;
    private Step testStepB;
    private StepList testStepList;

    @BeforeEach
    void runBefore() {
        testCookbook = new Cookbook();
        testIngredientList = new IngredientList();
        testIngredient = new Ingredient("Egg", 1, "whole");
        testStepA = new Step(1, "Add oil to pan");
        testStepB = new Step(2, "Crack egg");
        testStepList = new StepList();
        testIngredientList.addIngredient(testIngredient);
        testStepList.addStep(testStepA);
        testStepList.addStep(testStepB);
        testRecipe = new Recipe("Fried Egg", testIngredientList, testStepList);

    }

    @Test
    void addRecipe() {
        testCookbook.addRecipe(testRecipe);
        assertEquals(1, testCookbook.length());
    }

    @Test
    void removeRecipe() {
        testCookbook.addRecipe(testRecipe);
        assertEquals(1, testCookbook.length());
        testCookbook.removeRecipe(testRecipe);
        assertEquals(0, testCookbook.length());
    }

}
