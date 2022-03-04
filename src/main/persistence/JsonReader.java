package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;


// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads cookbook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Cookbook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCookbook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses cookbook from JSON object and returns it
    private Cookbook parseCookbook(JSONObject jsonObject) {
        Cookbook c = new Cookbook();
        addRecipes(c, jsonObject);
        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses recipes from JSON object and adds them to Cookbook
    private void addRecipes(Cookbook c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(c, nextRecipe);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses recipe from JSON object and adds it to cookbook
    private void addRecipe(Cookbook c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        IngredientList ing = readIngredient(c, jsonObject);
        StepList step = readStep(c, jsonObject);

        Recipe recipe = new Recipe(name, ing, step);
        c.addRecipe(recipe);

    }

    // MODIFIES: c
    // EFFECTS: parses ingredient from JSON object and adds it to cookbook
    private IngredientList readIngredient(Cookbook c, JSONObject jsonObject) {
        JSONObject ingredient = jsonObject.getJSONObject("ingredients");
        IngredientList ingredientList = jsonToIngredientList(ingredient);
        return ingredientList;
    }

    // MODIFIES: c
    // EFFECTS: parses step from JSON object and adds it to cookbook
    private StepList readStep(Cookbook c, JSONObject jsonObject) {
        JSONObject step = jsonObject.getJSONObject("steps");
        StepList stepList = jsonToStepList(step);
        return stepList;
    }

    // helper
    // EFFECTS: gets components of ingredient list from JSON array and converts objects back into an ingredientList
    private IngredientList jsonToIngredientList(JSONObject jsonObject) {
        IngredientList ingList = new IngredientList();

        JSONArray jsonArray = jsonObject.getJSONArray("ingredient list");
        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            String name = object.getString("ingredient name");
            Double amount = object.getDouble("ingredient amount");
            String unit = object.getString("ingredient unit");
            Ingredient ing = new Ingredient(name, amount, unit);
            ingList.addIngredient(ing);
        }
        return ingList;

    }

    // helper
    // EFFECTS: gets components of step list from JSON array and converts objects back into an stepList
    private StepList jsonToStepList(JSONObject jsonObject) {
        StepList stepList = new StepList();

        JSONArray jsonArray = jsonObject.getJSONArray("step list");
        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            int number = object.getInt("step number");
            String instruction = object.getString("instructions");
            Step step = new Step(number, instruction);
            stepList.addStep(step);
        }
        return stepList;

    }

}

