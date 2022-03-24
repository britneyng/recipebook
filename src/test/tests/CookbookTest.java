package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookbookTest {

    private Cookbook testCookbook;
    private Recipe testRecipeA;
    private Recipe testRecipeB;
    private Recipe testRecipeC;

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
        testRecipeA = new Recipe("Fried Egg", testIngredientList, testStepList);
        testRecipeB = new Recipe("Fried Egg Version 2", testIngredientList, testStepList);
        testRecipeC = new Recipe("Fried Egg Version 3", testIngredientList, testStepList);

    }

    @Test
    void addRecipe() {
        testCookbook.addRecipe(testRecipeA);
        assertEquals(1, testCookbook.length());
    }

    @Test
    void removeRecipe() {
        testCookbook.addRecipe(testRecipeA);
        assertEquals(1, testCookbook.length());
        testCookbook.removeRecipe(testRecipeA);
        assertEquals(0, testCookbook.length());
        Recipe tester = new Recipe("tester", testIngredientList, testStepList);
        testCookbook.addRecipe(tester);
        assertEquals(1, testCookbook.length());
        testCookbook.removeRecipe(testRecipeA);
        assertEquals(1, testCookbook.length());


    }

    @Test
    void findRecipe() {
        testCookbook.addRecipe(testRecipeA);
        testCookbook.addRecipe(testRecipeB);
        testCookbook.addRecipe(testRecipeC);
        assertEquals(3, testCookbook.length());
        assertEquals(testRecipeB, testCookbook.findRecipe("Fried Egg Version 2"));
        assertEquals(testRecipeC, testCookbook.findRecipe("Fried Egg Version 3"));
        assertEquals(null, testCookbook.findRecipe("Non-existent recipe"));

    }

    @Test
    void getRecipeList() {
        testCookbook.addRecipe(testRecipeA);
        testCookbook.addRecipe(testRecipeB);
        testCookbook.addRecipe(testRecipeC);
        assertEquals(3, testCookbook.getRecipeList().size());
    }

    @Test
    void testPrintRecipe() {
        testCookbook.addRecipe(testRecipeA);
        testCookbook.addRecipe(testRecipeB);
        assertEquals(null, testCookbook.printRecipe("Fried Egg Version 1"));
        assertEquals("Fried Egg Version 2"
                        + "\n"
                        + "\n"
                        + "Ingredients:"
                        + "\n"
                        + "Egg, 1.0 whole"
                        + "\n"
                        + "\n"
                        + "Steps:"
                        + "\n"
                        + "1. Add oil to pan"
                        + "\n"
                        + "2. Crack egg"
                        + "\n"
                , testCookbook.printRecipe("Fried Egg Version 2"));
    }

}
