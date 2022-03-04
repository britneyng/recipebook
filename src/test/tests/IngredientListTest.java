package tests;

import model.Ingredient;
import model.IngredientList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientListTest {

    private Ingredient ingredientA;
    private Ingredient ingredientB;
    private Ingredient ingredientC;
    private IngredientList testList;


    @BeforeEach
    void runBefore() {
        ingredientA = new Ingredient("Chicken broth", 0.5, "cups");
        ingredientB = new Ingredient("Chili sauce", 1, "tbsp");
        ingredientC = new Ingredient("Potato", 2, "whole");
        testList = new IngredientList();
    }

    @Test
    void addIngredient() {
        testList.addIngredient(ingredientA);
        testList.addIngredient(ingredientB);
        testList.addIngredient(ingredientC);
        assertTrue(testList.hasIngredient(ingredientA));
        assertTrue(testList.hasIngredient(ingredientB));
        assertTrue(testList.hasIngredient(ingredientC));

    }

    @Test
    void removeIngredient() {
        testList.addIngredient(ingredientA);
        testList.addIngredient(ingredientB);
        assertTrue(testList.hasIngredient(ingredientA));
        assertTrue(testList.hasIngredient(ingredientB));
        testList.removeIngredient(ingredientA);
        testList.removeIngredient(ingredientB);
        assertFalse(testList.hasIngredient(ingredientA));
        assertFalse(testList.hasIngredient(ingredientB));
    }

    @Test
    void hasIngredientTest() {
        assertFalse(testList.hasIngredient(ingredientA));
        testList.addIngredient(ingredientA);
        testList.addIngredient(ingredientB);
        testList.addIngredient(ingredientC);
        assertTrue(testList.hasIngredient(ingredientA));
        assertTrue(testList.hasIngredient(ingredientB));
        assertTrue(testList.hasIngredient(ingredientC));

    }

    @Test
    void getIngredientListTest() {
        testList.addIngredient(ingredientA);
        testList.addIngredient(ingredientB);
        assertEquals("Chicken broth, 0.5 cups\n" +
                "Chili sauce, 1.0 tbsp" + "\n", testList.getIngredientList());
    }


}
