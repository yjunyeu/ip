package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command{
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing a task from the task list.
     *
     * @param tasks The task list containing the task.
     * @param ui The user interface to display messages.
     * @param storage The storage system for saving data.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(tasks.deleteTask(index));
    }
}
