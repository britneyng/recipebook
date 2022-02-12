package ui;

import model.*;

import java.util.Scanner;

// Code referenced from TellerApp
public class CookbookApp {

    private Scanner input;
    private Cookbook cookbook;
    private IngredientList ingredientList;
    private StepList stepList;
    private Ingredient ingredient;
    private Step step;
    private Recipe recipe;

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
            viewRecipes();
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
        System.out.println("\tv -> view recipes");
        System.out.println("\tw -> write a recipe");
        System.out.println("\tr -> remove recipe");
        System.out.println("\tq -> quit");
    }

    private void createRecipe() {
        System.out.println("Name your recipe:");
        String recipeName = input.next();

        createIngredientList(ingredientList);
        writeStepList(stepList);
        this.recipe = new Recipe(recipeName, ingredientList, stepList);
        this.cookbook = new Cookbook();
        cookbook.addRecipe(this.recipe);
        System.out.println("The recipe has been added to the Cookbook :)");

    }

    private void createIngredientList(IngredientList ingredientList) {
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
            ingredientList = new IngredientList();
            ingredientList.addIngredient(ingredient);
            ingredientList.getIngredientList();

        }
    }

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

    public void removeRecipe() {
        System.out.println("Which recipe would you like to remove? Enter the name: ");
        listRecipes();
        String recipeToBeRemoved = input.next();
        cookbook.removeRecipe(cookbook.findRecipe(recipeToBeRemoved));


    }



    public void viewRecipes() {
        listRecipes();

    }


    // EFFECTS: return a list of added recipes in the Cookbook
    public void listRecipes() {
        for (Recipe r : cookbook.getRecipes()) {
            System.out.println(recipe.getRecipeTitle());
        }

    }

}

