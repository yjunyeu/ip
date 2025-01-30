package ui;
import java.util.Scanner;

/**
 * Handles user interaction by displaying messages and reading input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui instance with a Scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a message enclosed in horizontal lines.
     *
     * @param content The message content to display.
     */
    public void displayMessage(String content) {
        String horizontalLine = "____________________________________________________________\n";
        String formatted = horizontalLine + content + horizontalLine;
        System.out.println(formatted);
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void displayWelcome() {
        displayMessage("""
                 Hello! I'm Waty
                 What can I do for you?
                """);
    }

    /**
     * Displays a goodbye message when the application exits.
     */
    public void displayBye() {
        displayMessage(" Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        displayMessage("Error: " + message);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine() + "\n";
    }
}
