package waty;

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
