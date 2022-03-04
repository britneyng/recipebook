package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// Represents a list of the ingredients that are required for a recipe
public class IngredientList implements Writable {

    private ArrayList<Ingredient> ingredientList;

    // EFFECTS: initializes a new list of ingredients to be used in a recipe
    public IngredientList() {
        this.ingredientList = new ArrayList<Ingredient>();
    }

    // REQUIRES: ingredient must already exist
    // MODIFIES: this
    // EFFECTS: adds an ingredient to the end of the cumulative list of ingredients
    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    // REQUIRES: ingredient to be removed must already be in the list
    // MODIFIES: this
    // EFFECTS: removes an ingredient from the list of recipeIngredients
    public void removeIngredient(Ingredient ingredient) {
        ingredientList.remove(ingredient);
    }

    // REQUIRES: ingredientList must have at least one ingredient in it
    // EFFECTS: returns the list of ingredients in the form of a string
    public String getIngredientList() {
        String result  = "";
        String ingredient = "";

        for (Ingredient i : ingredientList) {
            ingredient =  i.getIngredientName();
            ingredient += ", ";
            ingredient += i.getIngredientAmount();
            ingredient += " ";
            ingredient += i.getIngredientUnit();
            ingredient += "\n";
            result += ingredient;
        }
        return result;
    }


    // primarily used as a tester method, may use later for recipe suggestions
    // EFFECTS: returns true if the ingredientList contains a given ingredient, false otherwise
    public Boolean hasIngredient(Ingredient ingredient) {
        if (ingredientList.contains(ingredient)) {
            return true;
        }
        return false;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray ingredientListToJSON() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : ingredientList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ingredient list", ingredientListToJSON());
        return json;
    }


}
