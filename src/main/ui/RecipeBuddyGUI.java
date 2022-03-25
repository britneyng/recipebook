package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Integer.parseInt;

// Button functionality was inspired by the AlarmSystem project:
// REPO: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

/**
 * Representation of the GUI for the RecipeBuddyApp
 */

public class RecipeBuddyGUI extends JFrame implements ListSelectionListener {

    private static final String IMAGE = "./images/splash.jpeg";
    private static final String JSON_STORE = "./data/cookbook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Cookbook cookbook = new Cookbook();
    private static final int WIDTH = 650;
    private static final int HEIGHT = 450;
    private static final int BUTTON_X_POS = 50;
    private static final int BUTTON_WIDTH = 125;
    private static final int BUTTON_HEIGHT = 45;
    private DefaultListModel model;
    JFrame frame;
    JList<Recipe> recipeList;
    JPanel displayPanel;
    JScrollPane listScrollPane;
    JButton addRecipeButton;
    JButton readRecipeButton;
    JButton removeRecipeButton;
    JButton saveButton;
    JButton loadButton;
    JTextField recipeTitleField;
    JTextField ingredientNameField;
    JTextField ingredientAmountField;
    JTextField ingredientUnitField;
    JTextField stepNum;
    JTextField stepInstructions;
    int createAnotherIng;
    int createAnotherStep;
    IngredientList ingList;
    StepList stepList;

    public RecipeBuddyGUI() {
        UIManager.put("OptionPane.minimumSize",new Dimension(350,150));
        initSplash();
        startFrame();
        initButtons();
        initPanels();
        initList();
        initFrame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.centreOnScreen();
        this.setVisible(true);
    }

    // EFFECTS: initialize the frame
    public void startFrame() {
        this.setTitle("Your RecipeBuddy!");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: initialize elements within the frame
    public void initFrame() {
        add(addRecipeButton);
        add(readRecipeButton);
        add(removeRecipeButton);
        add(saveButton);
        add(loadButton);
        add(displayPanel);
        add(listScrollPane);
    }

    // EFFECTS: initialize the display panel and button panel
    public void initPanels() {
        displayPanel = new JPanel();
        displayPanel.setLayout(null);
        displayPanel.setBounds(250, 50, 350, 300);
        displayPanel.setBackground(Color.lightGray);
        displayPanel.setVisible(true);

    }

    // EFFECTS: initialize the buttons
    public void initButtons() {
        addRecipeButton = new JButton(new AddRecipeAction());
        addRecipeButton.setBounds(BUTTON_X_POS, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
        addRecipeButton.setText("Write a Recipe");
        addRecipeButton.setVisible(true);

        readRecipeButton = new JButton(new ReadRecipeAction());
        readRecipeButton.setBounds(BUTTON_X_POS, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        readRecipeButton.setText("Read a Recipe");
        readRecipeButton.setVisible(true);

        removeRecipeButton = new JButton(new RemoveRecipeAction());
        removeRecipeButton.setBounds(BUTTON_X_POS, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        removeRecipeButton.setText("Remove a Recipe");
        removeRecipeButton.setVisible(true);

        saveButton = new JButton(new SaveRecipesAction());
        saveButton.setBounds(BUTTON_X_POS, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        saveButton.setText("Save Cookbook");
        saveButton.setVisible(true);

        loadButton = new JButton(new LoadRecipesAction());
        loadButton.setBounds(BUTTON_X_POS, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
        loadButton.setText("Load Cookbook");
        loadButton.setVisible(true);

    }

    // EFFECTS: initialize the JList that displays each recipe of the cookbook
    public void initList() {
        model = new DefaultListModel();
        recipeList = new JList(cookbook.getRecipeList().toArray());
        recipeList.setModel(model);
        refreshModel();
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.setSelectedIndex(0);
        recipeList.addListSelectionListener(this);
        recipeList.setVisibleRowCount(3);
        recipeList.setFixedCellHeight(27);
        listScrollPane = new JScrollPane(recipeList);
        listScrollPane.setBounds(250, 50, 350, 300);
        displayPanel.add(listScrollPane, BorderLayout.CENTER);

    }

    /**
     * Represents the action to be taken when the user wants a recipe to be added to the cookbook.
     */
    private class AddRecipeAction extends AbstractAction {

        AddRecipeAction() {
            super("Add recipe");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            recipeTitleField();
            Object[] titleField = {"Recipe Title", recipeTitleField};
            ingList = new IngredientList();
            stepList = new StepList();
            int response = JOptionPane.showConfirmDialog(null, titleField,
                    "Name your recipe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == (JOptionPane.OK_OPTION)) {
                promptUserForIngredients();

                cookbook.addRecipe(new Recipe(recipeTitleField.getText(), ingList, stepList));
                model.addElement(new Recipe(recipeTitleField.getText(), ingList, stepList));
                refreshModel();

            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to view a particular recipe in the cookbook.
     */
    private class ReadRecipeAction extends AbstractAction {

        ReadRecipeAction() {
            super("Read recipe");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            int index = recipeList.getSelectedIndex();
            String recipeName = cookbook.getRecipeList().get(index).getRecipeTitle();
            JTextArea readArea = new JTextArea();
            readArea.append(cookbook.printRecipe(recipeName));
            JScrollPane scrollPane = new JScrollPane(readArea);
            JOptionPane.showMessageDialog(frame, scrollPane);

        }
    }

    /**
     * Represents the action to be taken when the user wants to remove a recipe from the cookbook.
     */
    private class RemoveRecipeAction extends AbstractAction {

        RemoveRecipeAction() {
            super("Remove recipe");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            int index = recipeList.getSelectedIndex();

            int response = (JOptionPane.showConfirmDialog(null,
                    "Would you like to remove the recipe "
                            + cookbook.getRecipeList().get(index).getRecipeTitle()
                            + " from the cookbook?",
                    "Remove recipe?", JOptionPane.YES_NO_OPTION));

            if (response == (JOptionPane.YES_OPTION)) {

                cookbook.getRecipeList().remove(index);
                model.removeElementAt(index);
                refreshModel();
                JOptionPane.showMessageDialog(null, "The recipe has been removed!");
            }

        }
    }

    /**
     * Represents the action to be taken when the user wants to save the cookbook to file.
     */
    private class SaveRecipesAction extends AbstractAction {

        SaveRecipesAction() {
            super("Save recipes");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(cookbook);
                jsonWriter.close();
                System.out.println("Saved recipes to " + JSON_STORE);
                JOptionPane.showMessageDialog(null, "Your recipes have been saved :)");
            } catch (FileNotFoundException ex) {
                System.out.println("File not found.");
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to load the cookbook from previous sessions.
     */
    private class LoadRecipesAction extends AbstractAction {

        LoadRecipesAction() {
            super("Load recipes");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cookbook = jsonReader.read();
                refreshModel();
                recipeList.revalidate();
                recipeList.repaint();
                System.out.println("Loaded cookbook from " + JSON_STORE);
            } catch (IOException ex) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }


    // EFFECTS: updates the model with an updated list of recipes in the Cookbook
    private void refreshModel() {
        int i = 0;
        model.clear();
        for (Recipe r : cookbook.getRecipeList()) {
            model.add(i, r.getRecipeTitle());
            i++;
        }
    }

    // EFFECTS: handles user input and prompts for ingredients to be added to the recipe
    private IngredientList promptUserForIngredients() {
        ingredientFields();
        Object[] ingredientInputs = {"Ingredient Name", ingredientNameField, "Ingredient Amount", ingredientAmountField,
                "Ingredient Unit", ingredientUnitField};
        do {
            setEmptyIngFields();
            createAnotherIng = JOptionPane.showConfirmDialog(null, ingredientInputs,
                    "Writer", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.CANCEL_OPTION == createAnotherIng || (checkEmptyIngFields() == true)) {
                int r = JOptionPane.showConfirmDialog(null,
                        "Are you done adding ingredients to your recipe?",
                        "Done adding ingredients?", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.YES_OPTION == r) {
                    promptUserForSteps();
                } else if (JOptionPane.NO_OPTION == r) {
                    promptUserForIngredients();
                }
            } else {
                ingList.addIngredient(new Ingredient(ingredientNameField.getText(),
                        Double.parseDouble(ingredientAmountField.getText()),
                        ingredientUnitField.getText()));
            }
        } while (createAnotherIng == JOptionPane.OK_OPTION); // INGREDIENT PANE OK
        return ingList;
    }

    // EFFECTS: handles user input and prompts for steps to be added to the recipe
    private StepList promptUserForSteps() {
        stepFields();
        Object[] stepInputs = {"Step Number", stepNum, "Step Instruction", stepInstructions,};
        do {
            setEmptyStepFields();
            createAnotherStep = JOptionPane.showConfirmDialog(null, stepInputs,
                    "Writer", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.CANCEL_OPTION == createAnotherStep || (checkEmptyStepFields() == true)) {
                int r = JOptionPane.showConfirmDialog(null,
                        "Are you done adding steps to your recipe?",
                        "Done adding steps?", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.NO_OPTION == r) {
                    promptUserForSteps();
                } else if (JOptionPane.YES_OPTION == r) {
                    return stepList;
                }

            } else {
                stepList.addStep(new Step(parseInt(stepNum.getText()), stepInstructions.getText()));
            }
        } while (createAnotherStep == JOptionPane.OK_OPTION); // STEP PANE OK
        return stepList;

    }

    private void recipeTitleField() {
        recipeTitleField = new JTextField();
    }

    // EFFECTS: initialize new text fields
    private void ingredientFields() {
        ingredientNameField = new JTextField();

        ingredientAmountField = new JTextField();
        ingredientUnitField = new JTextField();
    }



    private void setEmptyIngFields() {
        ingredientNameField.setText("");
        ingredientAmountField.setText("");
        ingredientUnitField.setText("");
    }

    private void setEmptyStepFields() {
        stepNum.setText("");
        stepInstructions.setText("");
    }

    // EFFECTS: check if any of the fields have been left blank and return true if so
    private Boolean checkEmptyIngFields() {
        return (ingredientNameField.equals(""))
                || (ingredientAmountField.equals(""))
                || (ingredientUnitField.equals(""));
    }

    // EFFECTS: check if either field is left blank and return true if so
    private Boolean checkEmptyStepFields() {
        return stepNum.equals("") || (stepInstructions.equals(""));
    }

    // EFFECTS: initializes the Step text fields
    private void stepFields() {
        stepNum = new JTextField("");
        stepInstructions = new JTextField("");

    }


    // EFFECTS: initializes a splash screen that appears while the main application is loading
    private void initSplash() {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel(new ImageIcon(IMAGE)));
        window.setBounds(460, 200, 500, 500);
        window.setVisible(true);
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            System.out.println("Window was interrupted. Please try again.");
        }
        window.setVisible(false);
        window.dispose();
    }

    // EFFECTS: centres the window on the screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }


}
