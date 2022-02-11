package model;

// Represents a single step in the list of steps required for a recipe

public class Step {

    private int stepNumber;
    private String instructions;

    // REQUIRES: stepNumber > 0
    // EFFECTS: constructs step with instruction and corresponding step number
    public Step(int stepNumber, String instructions) {
        this.stepNumber = stepNumber;
        this.instructions = instructions;
    }

    // EFFECTS: return the number of the step
    public int getStepNumber() {
        return stepNumber;
    }

    // EFFECTS: return the instructions for a step
    public String getInstructions() {
        return instructions;
    }
}
