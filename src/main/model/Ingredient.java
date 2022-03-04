package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents an ingredient that will be used in a recipe
public class Ingredient implements Writable {

    protected String name;
    protected double amount;
    protected String unit;

    // REQUIRES: name, amount, and unit must not be empty
    // EFFECTS: initialize an ingredient with a name, amount, and unit of measurement
    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;

    }

    // getters

    public String getIngredientName() {
        return name;
    }

    public double getIngredientAmount() {
        return amount;
    }

    public String getIngredientUnit() {
        return unit;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ingredient name", name);
        json.put("ingredient amount", amount);
        json.put("ingredient unit", unit);
        return json;
    }
}
