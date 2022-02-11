package tests;

import model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    private Ingredient ingredientA;
    private Ingredient ingredientB;
    private Ingredient ingredientC;


    @BeforeEach
    void runBefore() {
        ingredientA = new Ingredient("Chicken broth", 0.5, "cups");
        ingredientB = new Ingredient("Chili sauce", 1, "tbsp");
        ingredientC = new Ingredient("Potato", 2, "whole");
    }

    @Test
    void getIngredientNameTest() {
        assertEquals("Chicken broth", ingredientA.getIngredientName());
        assertEquals("Chili sauce", ingredientB.getIngredientName());
        assertEquals("Potato", ingredientC.getIngredientName());
    }

    @Test
    void getIngredientAmountTest() {
        assertEquals(0.5, ingredientA.getIngredientAmount());
        assertEquals(1, ingredientB.getIngredientAmount());
        assertEquals(2, ingredientC.getIngredientAmount());
    }

    @Test
    void getIngredientUnitTest(){
        assertEquals("cups", ingredientA.getIngredientUnit());
        assertEquals("tbsp", ingredientB.getIngredientUnit());
        assertEquals("whole", ingredientC.getIngredientUnit());
    }
}
