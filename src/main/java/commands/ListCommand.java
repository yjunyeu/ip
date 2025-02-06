package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks The task list to be displayed.
     * @param ui The user interface to display messages.
     * @param storage The storage system (not modified in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(tasks.listTasks());
    }
}
