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


/**
 * Representation of the GUI for the RecipeBuddyApp
 */
public class RecipeBuddyGUI extends JFrame implements ListSelectionListener {

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

    public RecipeBuddyGUI() {
        initFrame();
        setupButtons();
        setupPanels();
        setupList();
        setupFrame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // new button -> action listener -> action performer ->
    }

    // EFFECTS: initialize the frame
    public void initFrame() {
        frame = new JFrame("Your RecipeBuddy!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        revalidate();
        repaint();
        setVisible(true);
    }

    // EFFECTS: setup elements within the frame
    public void setupFrame() {
        add(addRecipeButton);
        add(readRecipeButton);
        add(removeRecipeButton);
        add(saveButton);
        add(loadButton);
        add(displayPanel);
        add(listScrollPane);
    }

    public void setupPanels() {
        displayPanel = new JPanel();
        displayPanel.setLayout(null);
        displayPanel.setBounds(250, 50, 350, 300);
        displayPanel.setBackground(Color.lightGray);
        displayPanel.setVisible(true);

    }

    public void setupButtons() {
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

    public void setupList() {
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

    @Override
    public void valueChanged(ListSelectionEvent e) {

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

            // for add recipe action
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
            JTextArea readArea = new JTextArea(10, 20);
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
                revalidate();
                repaint();
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


    // EFFECTS: loops through every recipe in the cookbook and fills the model with an updated list of recipes
    private void refreshModel() {
        int i = 0;
        model.clear();
        for (Recipe r : cookbook.getRecipeList()) {
            model.add(i, r.getRecipeTitle());
            i++;
        }
    }


}