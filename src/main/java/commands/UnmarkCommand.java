package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a completed task (mark as not done).
 */
public class UnmarkCommand extends Command{
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by updating a task's status to not completed.
     *
     * @param tasks The task list containing the task.
     * @param ui The user interface to display messages.
     * @param storage The storage system (not modified in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(" OK, I've marked this task as not done yet:\n " + tasks.unmarkTask(index) + "\n");
    }
}
