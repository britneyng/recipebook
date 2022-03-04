package persistence;

import model.Cookbook;
import model.Recipe;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Cookbook c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCookbook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCookbook.json");
        try {
            Cookbook c = reader.read();
            assertEquals(new ArrayList<>(), c.getRecipeList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCookbook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCookbook.json");
        try {
            Cookbook c = reader.read();
            List<Recipe> recipes = c.getRecipeList();
            assertEquals(1, recipes.size());
            checkRecipe("Fried egg", recipes.get(0).getIngredients(), recipes.get(0).getSteps(), recipes.get(0));
//            checkRecipe("Toast", recipes.get(1).getIngredients(), recipes.get(1).getSteps(), recipes.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
