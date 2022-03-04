package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Code referenced from TellerApp
// Repo:


// Referenced code from JsonSerializationDemo - note that classes contained in the persistence folder utilize this code
// Repo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


public class RecipeBuddyApp {

    private static final String JSON_STORE = "./data/cookbook.json";
    private Scanner input;
    private Cookbook cookbook = new Cookbook();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Cookbook app
    public RecipeBuddyApp() {
        runRecipeBuddyApp();
    }

    public void runRecipeBuddyApp() {
        Boolean keepGoing = true;
        String command = null;

        System.out.println(
                "Welcome! This is a cookbook app that is designed to help you manage your recipes."
                        + " Try adding one!");

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("view")) {
            viewAllRecipes();
        } else if (command.equals("read")) {
            readRecipe();
        } else if (command.equals("write")) {
            createRecipe();
        } else if (command.equals("remove")) {
            removeRecipe();
        } else if (command.equals("save")) {
            saveCookbook();
        } else if (command.equals("load")) {
            loadCookbook();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tview -> view list of added recipes");
        System.out.println("\tread -> read single recipe");
        System.out.println("\twrite -> write a recipe");
        System.out.println("\tremove -> remove recipe");
        System.out.println("\tsave -> save recipes to file");
        System.out.println("\tload -> load recipes from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a recipe with a name, ingredient list, and step list
    private void createRecipe() {
        System.out.println("Name your recipe:");
        String recipeName = input.next();
        cookbook.addRecipe(new Recipe(recipeName, createIngredientList(), writeStepList()));
        System.out.println("The recipe has been added to the Cookbook :)");

    }

    // EFFECTS: creates an ingredient list for the recipe
    private IngredientList createIngredientList() {
        System.out.println("Let's add some ingredients!");
        System.out.println("How many ingredients are in this recipe?");
        int quantity = input.nextInt();
        IngredientList ingList = new IngredientList();

        for (int i = 0; (i != quantity); i++) {
            System.out.println("What's the name of your ingredient?");
            String ingredientName = input.next();
            System.out.println("Enter the amount: ");
            double ingredientAmount = input.nextDouble();
            System.out.println("Enter the unit: ");
            String ingredientUnit = input.next();

            Ingredient ing = new Ingredient(ingredientName, ingredientAmount, ingredientUnit);
            ingList.addIngredient(ing);
        }
        return ingList;
    }

    // EFFECTS: creates a step list for the recipe
    public StepList writeStepList() {
        System.out.println("Let's add some steps for your recipe!");
        System.out.println("How many steps are in this recipe?");

        StepList stepList = new StepList();
        int quantity = input.nextInt();
        for (int i = 0; (i != quantity); i++) {
            System.out.println("Enter the step number: ");
            int stepNumber = input.nextInt();
            System.out.println("Enter the instructions for this step: ");
            String instruction = input.next();

            Step step = new Step(stepNumber, instruction);
            stepList.addStep(step);
        }
        return stepList;
    }

    // EFFECTS: lists recipes to be viewed by the user
    public void viewAllRecipes() {
        listRecipes();

    }

    // EFFECTS: view single recipe in detail
    public void readRecipe() {
        System.out.println("Which recipe would you like to access?");
        listRecipes();
        String recipeToView = input.next();
        cookbook.findRecipe(recipeToView);
        cookbook.printRecipe(recipeToView);

    }

    // EFFECTS: removes recipe from Cookbook if input matches an existing recipe name
    public void removeRecipe() {
        System.out.println("Which recipe would you like to remove? Enter the name: ");
        listRecipes();
        String recipeToBeRemoved = input.next();
        cookbook.removeRecipe(cookbook.findRecipe(recipeToBeRemoved));
    }

    // EFFECTS: return a list of added recipes in the Cookbook
    public void listRecipes() {
        for (Recipe r : cookbook.getRecipeList()) {
            System.out.println(r.getRecipeTitle());
        }
    }

    // EFFECTS: saves the cookbook to file
    private void saveCookbook() {
        try {
            jsonWriter.open();
            jsonWriter.write(cookbook);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads cookbook from file
    private void loadCookbook() {
        try {
            cookbook = jsonReader.read();
            System.out.println("Loaded cookbook from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}



