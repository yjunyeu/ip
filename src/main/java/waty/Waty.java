//package waty;
//
//import commands.Command;
//import exceptions.InvalidArgumentException;
//import exceptions.InvalidCommandException;
//import parser.Parser;
//import storage.Storage;
//import task.TaskList;
//import ui.Ui;
//
///**
// * The main class for the Waty chatbot application.
// */
//public class Waty {
//    private final Ui ui;
//    private final Storage storage;
//    private final TaskList tasks;
//    private final Parser parser;
//
//    /**
//     * Constructs a new Waty instance, initializing components.
//     */
//    public Waty() {
//        this.ui = new Ui();
//        this.storage = new Storage(ui);
//        this.tasks = new TaskList(storage.loadTasks());
//        this.parser = new Parser();
//    }
//
//
//    /**
//     * Runs the main loop of the application, handling user input and executing commands.
//     */
//    public void run() {
//        ui.displayWelcome();
//        boolean isRunning = true;
//        while (isRunning) {
//            try {
//                String userInput = ui.readCommand();
//                Command command = parser.parse(userInput);
//                command.execute(tasks, ui, storage);
//                isRunning = command.isRunning();
//            } catch (InvalidArgumentException | InvalidCommandException e) {
//                ui.displayMessage(e.getMessage());
//            } catch (Exception e) {
//                ui.displayError("ERRORRRRR!!! THIS IS WEIRD");
//            }
//
//        }
//    }
//
//    /**
//     * The main entry point of the application.
//     *
//     * @param args Command-line arguments (not used).
//     */
//    public static void main(String[] args) {
//        new Waty().run();
//    }
//
//}
package waty;

import commands.Command;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The main class for the Waty chatbot application.
 */
public class Waty {
    private Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a new Waty instance, initializing components.
     */
    public Waty() {
        this.ui = null;
        this.storage = new Storage(ui);
        this.tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }

    /**
     * Sets the UI instance for interaction.
     *
     * @param ui The UI instance to be used by Waty.
     */
    public void setUi(Ui ui) {
        this.ui = ui;
    }

    /**
     * Handles user input and executes commands.
     *
     * @param userInput The user's command.
     * @return The chatbot's response.
     */
    public String handleUserInput(String userInput) {
        try {
            Command command = parser.parse(userInput);
            command.execute(tasks, ui, storage);
            if (!command.isRunning()) {
                System.exit(0);
            }
            return "Command executed successfully.";
        } catch (InvalidArgumentException | InvalidCommandException e) {
            ui.displayMessage(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            ui.displayError("ERRORRRRR!!! THIS IS WEIRD");
            return "ERRORRRRR!!! THIS IS WEIRD";
        }
    }

    /**
     * Runs the main loop of the application, handling user input.
     * (This method is now obsolete for GUI use but remains for CLI compatibility.)
     */
    public void run() {
        ui.displayWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isRunning = command.isRunning();
            } catch (InvalidArgumentException | InvalidCommandException e) {
                ui.displayMessage(e.getMessage());
            } catch (Exception e) {
                ui.displayError("ERRORRRRR!!! THIS IS WEIRD");
            }
        }
    }

    /**
     * The main entry point of the application.
     * (No longer used with JavaFX, as Launcher.java starts the GUI.)
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Waty().run();
    }

    // Getters to allow MainWindow to access components
    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Parser getParser() {
        return parser;
    }
}
