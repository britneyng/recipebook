package model;

import java.util.ArrayList;

public class StepList {

    private ArrayList<Step> stepList;

    // EFFECTS: initialize stepList with an empty ArrayList
    public StepList() {
        this.stepList = new ArrayList<Step>();
    }

    // REQUIRES: steps should be added in sequentially; step cannot be 0
    // MODIFIES: this
    // EFFECTS: add a step to the list
    public void addStep(Step step) {
        stepList.add(step);
    }

    // REQUIRES: step must already exist in the list
    // MODIFIES: this
    // EFFECTS: remove a specific step from the list
    public void removeStep(Step step) {
        for (int i = 0; i < stepList.size(); i++) {
            if (stepList.get(i) == step) {
                stepList.remove(step);
                // look through the entire StepList and if a list matches a given step, remove it
            }
        }
    }

    // REQUIRES: num must not be 0
    // EFFECTS: return the step with given number
    public Step getStep(int stepNum) {
        return stepList.get(stepNum - 1);
        // items are stored with 0 based indexing, so subtract 1 to retrieve the correct step

    }

    // EFFECTS: return true is StepList contains specified step and false otherwise
    public Boolean hasStep(Step step) {
        if (stepList.contains(step)) {
            return true;
        }
        return false;
    }

}
