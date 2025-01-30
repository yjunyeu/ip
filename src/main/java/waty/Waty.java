package waty;

import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import commands.Command;

/**
 * The main class for the Waty chatbot application.
 */
public class Waty {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a new Waty instance, initializing components.
     */
    public Waty() {
        this.ui = new Ui();
        this.storage = new Storage(ui);
        this.tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }


    /**
     * Runs the main loop of the application, handling user input and executing commands.
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
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Waty().run();
    }

}
