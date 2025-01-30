package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import task.ToDo;

/**
 * Represents a command to add a ToDo task.
 */
public class AddTodoCommand extends Command{

    private final ToDo todo;

    /**
     * Constructs an AddTodoCommand with the given task description.
     *
     * @param description The description of the ToDo task.
     */
    public AddTodoCommand(String description) {
        this.todo = new ToDo(description);
    }

    /**
     * Executes the command by adding a ToDo task to the task list.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface to display messages.
     * @param storage The storage system (not modified in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int size = tasks.addTask(todo);
        ui.displayMessage(" Got it. I've added this task:\n" + " " + todo + "\n" + " Now you have " +
                size + " tasks in the list.\n");
    }
}
