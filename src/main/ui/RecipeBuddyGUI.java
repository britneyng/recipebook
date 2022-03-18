package ui;

import com.sun.java.swing.action.SaveAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
Representation of the GUI for the RecipeBuddyApp
 */
public class RecipeBuddyGUI extends JFrame implements ActionListener {

    private static final int WIDTH = 750;
    private static final int HEIGHT = 500;
    private static final int BUTTON_X_POS = 50;
    private static final int BUTTON_WIDTH = 125;
    private static final int BUTTON_HEIGHT = 35;
    JFrame frame;
    JButton addRecipeButton;
    JButton readRecipeButton;
    JButton removeRecipeButton;
    JButton saveButton;
    JButton loadButton;

    public RecipeBuddyGUI() {
        init();
        setupButtons();
        setupFrame();

        // new button -> action listener -> action performer ->
    }

    // EFFECTS: initialize the frame
    public void init() {
        frame = new JFrame("Your RecipeBuddy");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.revalidate();
    }

    // EFFECTS: setup elements within the frame
    public void setupFrame() {
        frame.add(addRecipeButton);
        frame.add(readRecipeButton);
        frame.add(removeRecipeButton);
        frame.add(saveButton);
        frame.add(loadButton);
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

        saveButton = new JButton();
        saveButton.setBounds(BUTTON_X_POS, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        saveButton.setText("Save Cookbook");
        saveButton.setVisible(true);

        loadButton = new JButton("Load");
        loadButton.setBounds(BUTTON_X_POS, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
        loadButton.setText("Load Cookbook");
        loadButton.setVisible(true);
    }

    /**
    Represents the action to be taken when the user wants a recipe to be added to the cookbook.
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
    Represents the action to be taken when the user wants to view a particular recipe in the cookbook.
     */
    private class ReadRecipeAction extends AbstractAction {

        ReadRecipeAction() {
            super("Read recipe");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // for read recipe action
        }
    }

    /**
    Represents the action to be taken when the user wants to remove a recipe from the cookbook.
     */
    private class RemoveRecipeAction extends AbstractAction {

        RemoveRecipeAction() {
            super("Remove recipe");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
