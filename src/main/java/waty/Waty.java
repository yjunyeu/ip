package waty;

import commands.Command;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The main class for the Waty application.
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
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Sets the UI instance for interaction.
     *
     * @param ui The UI instance to be used by Waty.
     */
    public void setUi(Ui ui) {
        this.ui = ui;
        this.storage.setUi(ui, tasks);
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
     * Retrieves the storage component of Waty.
     *
     * @return The {@link Storage} instance used by Waty.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Retrieves the task list managed by Waty.
     *
     * @return The {@link TaskList} instance storing tasks.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Retrieves the parser used to interpret user commands.
     *
     * @return The {@link Parser} instance handling command parsing.
     */
    public Parser getParser() {
        return parser;
    }
}
