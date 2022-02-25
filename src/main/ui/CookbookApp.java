package ui;

import model.*;

import java.util.Scanner;

// Code referenced from TellerApp
public class CookbookApp {

    private Scanner input;
    private Cookbook cookbook = new Cookbook();
    private IngredientList ingredientList;
    private StepList stepList;
    private Ingredient ingredient;
    private Step step;

    // EFFECTS: runs the Cookbook app
    public CookbookApp() {
        runCookbook();

    }

    public void runCookbook() {
        Boolean keepGoing = true;
        String command = null;

        System.out.println(
                "Welcome! This is a cookbook app that is designed to help you manage your recipes."
                        + "Try adding one!");

        input = new Scanner(System.in);
        input.useDelimiter("\n");

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
        if (command.equals("v")) {
            viewAllRecipes();
        } else if (command.equals("w")) {
            createRecipe();
        } else if (command.equals("r")) {
            removeRecipe();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view list of added recipes");
        System.out.println("\tw -> write a recipe");
        System.out.println("\tr -> remove recipe");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: Creates a recipe with a name, ingredient list, and step list
    private void createRecipe() {
        System.out.println("Name your recipe:");
        String recipeName = input.next();

        createIngredientList(ingredientList);
        writeStepList(stepList);
        cookbook.addRecipe(new Recipe(recipeName, ingredientList, stepList));
        System.out.println("The recipe has been added to the Cookbook :)");

    }

    // EFFECTS: creates an ingredient list for the recipe
    private void createIngredientList(IngredientList ingredients) {
        System.out.println("Let's add some ingredients!");
        System.out.println("How many ingredients are in this recipe?");
        int quantity = input.nextInt();

        for (int i = 0; (i != quantity); i++) {
            System.out.println("What's the name of your ingredient?");
            String ingredientName = input.next();
            System.out.println("Enter the amount: ");
            double ingredientAmount = input.nextDouble();
            System.out.println("Enter the unit: ");
            String ingredientUnit = input.next();

            ingredient =  new Ingredient(ingredientName, ingredientAmount, ingredientUnit);
            ingredients = new IngredientList();
            ingredients.addIngredient(ingredient);
            ingredients.getIngredientList();

        }
    }

    // EFFECTS: creates a step list for the recipe
    public void writeStepList(StepList stepList) {
        System.out.println("Let's add some steps for your recipe!");
        System.out.println("How many steps are in this recipe?");

        int quantity = input.nextInt();
        for (int i = 0; (i != quantity); i++) {
            System.out.println("Enter the step number: ");
            int stepNumber = input.nextInt();
            System.out.println("Enter the instructions for this step: ");
            String instruction = input.next();
            step = new Step(stepNumber, instruction);
            stepList = new StepList();
            stepList.addStep(step);
        }
    }

    // EFFECTS: lists recipes to be viewed by the user
    public void viewAllRecipes() {
        listRecipes();

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

    // EFFECTS: view a recipe in detail with a list of ingredients and stepse
//    public void viewDetailedRecipe(){
//
//    }
}

