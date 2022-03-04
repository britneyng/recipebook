package model;

// Represents a single step in the list of steps required for a recipe

import org.json.JSONObject;
import persistence.Writable;

public class Step implements Writable {

    private int stepNumber;
    private String instructions;

    // REQUIRES: stepNumber > 0
    // EFFECTS: constructs step with instruction and corresponding step number
    public Step(int stepNumber, String instructions) {
        this.stepNumber = stepNumber;
        this.instructions = instructions;
    }

    // getters

    public int getStepNumber() {
        return stepNumber;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("step number", stepNumber);
        json.put("instructions", instructions);
        return json;
    }
}
