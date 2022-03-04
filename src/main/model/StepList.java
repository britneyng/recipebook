package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class StepList implements Writable {

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
    // EFFECTS: remove a step from existing StepList
    public void removeStep(Step step) {
        for (int i = 0; i < stepList.size(); i++) {
            if (stepList.get(i) == step) {
                stepList.remove(step);
            }
        }
    }

    // EFFECTS: return a list of the steps for a recipe
    public String getSteps() {
        String result = "";
        String step = "";

        for (Step s : stepList) {
            step = s.getInstructions();
            step += "\n";
            result += step;
        }
        return result;
    }

    // use this to handle exception from removeStep later!
    // EFFECTS: return true is StepList contains specified step and false otherwise
    public Boolean hasStep(Step step) {
        if (stepList.contains(step)) {
            return true;
        }
        return false;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray stepListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Step s : stepList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("step list", stepListToJson());
        return json;
    }
}
