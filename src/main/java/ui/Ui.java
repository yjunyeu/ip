//package ui;
//
//import java.util.Scanner;
//
///**
// * Handles user interaction by displaying messages and reading input.
// */
//public class Ui {
//    private final Scanner scanner;
//
//    /**
//     * Constructs a Ui instance with a Scanner to read user input.
//     */
//    public Ui() {
//        this.scanner = new Scanner(System.in);
//    }
//
//    /**
//     * Displays a message enclosed in horizontal lines.
//     *
//     * @param content The message content to display.
//     */
//    public void displayMessage(String content) {
//        String horizontalLine = "____________________________________________________________\n";
//        String formatted = horizontalLine + content + horizontalLine;
//        System.out.println(formatted);
//    }
//
//    /**
//     * Displays a welcome message when the application starts.
//     */
//    public void displayWelcome() {
//        displayMessage("""
//                 Hello! I'm Waty
//                 What can I do for you?
//                """);
//    }
//
//    /**
//     * Displays a goodbye message when the application exits.
//     */
//    public void displayBye() {
//        displayMessage(" Bye. Hope to see you again soon!\n");
//    }
//
//    /**
//     * Displays an error message.
//     *
//     * @param message The error message to display.
//     */
//    public void displayError(String message) {
//        displayMessage("Error: " + message);
//    }
//
//    /**
//     * Reads a command from the user input.
//     *
//     * @return The user input as a string.
//     */
//    public String readCommand() {
//        return scanner.nextLine() + "\n";
//    }
//}

package ui;

import javafx.application.Platform;
import waty.MainWindow;

/**
 * Handles user interaction by displaying messages and reading input.
 */
public class Ui {
    private MainWindow mainWindow;

    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void displayMessage(String message) {
        Platform.runLater(() -> mainWindow.displayBotMessage(message));
    }

    public void displayError(String errorMessage) {
        Platform.runLater(() -> mainWindow.displayBotMessage("Error: " + errorMessage));
    }

    public void displayWelcome() {
        Platform.runLater(() -> mainWindow.displayBotMessage("Hello! I'm Waty. What can I do for you?"));
    }

    public void displayBye() {
        Platform.runLater(() -> mainWindow.displayBotMessage("Bye. Hope to see you again soon!"));
    }

    public String readCommand() {
        return mainWindow.getUserInput(); // Retrieve user input from MainWindow
    }
}

