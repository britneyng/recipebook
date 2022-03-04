package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Cookbook c = new Cookbook();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCookbook() {
        try {
            Cookbook c = new Cookbook();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCookbook.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCookbook.json");
            c = reader.read();
            assertEquals(new ArrayList<>(), c.getRecipeList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCookbook() {
        try {
            Cookbook c = new Cookbook();
            Ingredient egg = new Ingredient("Egg", 1, "Whole");
            IngredientList ingList = new IngredientList();
            ingList.addIngredient(egg);
            Step stepA = new Step(1, "Fry the egg");
            StepList stepList = new StepList();
            stepList.addStep(stepA);
            c.addRecipe(new Recipe("Fried egg", ingList, stepList));


// writes ingredients and steps to the wrong recipe if more than one is added?
            Ingredient bread = new Ingredient("Bread", 1, "Slice");
            IngredientList ingListB = new IngredientList();
            ingListB.addIngredient(bread);
            Step stepB = new Step(1, "Toast the bread");
            StepList stepListB = new StepList();
            stepListB.addStep(stepB);
            c.addRecipe(new Recipe("Toast", ingListB, stepListB));


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCookbook.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCookbook.json");
            c = reader.read();
            List<Recipe> recipes = c.getRecipeList();
            assertEquals(2, recipes.size());
            checkRecipe("Fried egg", recipes.get(0).getIngredients(), recipes.get(0).getSteps(), recipes.get(0));
            checkRecipe("Toast", recipes.get(1).getIngredients(), recipes.get(1).getSteps(), recipes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
