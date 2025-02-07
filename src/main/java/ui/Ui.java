package ui;

import javafx.application.Platform;
import waty.MainWindow;

/**
 * Handles user interaction by displaying messages and reading input.
 */
public class Ui {
    private final MainWindow mainWindow;

    /**
     * Constructs a {@code Ui} instance to manage interactions between the user and Waty.
     *
     * @param mainWindow The main window controller of the JavaFX application.
     */
    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Displays a message from Waty in the chat window.
     *
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        Platform.runLater(() -> mainWindow.displayBotMessage(message));
    }

    /**
     * Displays an error message in the chat window.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void displayError(String errorMessage) {
        Platform.runLater(() -> mainWindow.displayBotMessage("Error: " + errorMessage));
    }

    /**
     * Displays the welcome message when Waty starts.
     */
    public void displayWelcome() {
        Platform.runLater(() -> mainWindow.displayBotMessage("Hello! I'm Waty. What can I do for you?"));
    }

    /**
     * Displays the farewell message before exiting Waty.
     */
    public void displayBye() {
        Platform.runLater(() -> mainWindow.displayBotMessage("Bye. Hope to see you again soon!"));
    }

    /**
     * Reads the user's input from the GUI.
     *
     * @return The text entered by the user in the input field.
     */
    public String readCommand() {
        return mainWindow.getUserInput();
    }
}

