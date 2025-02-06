package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by updating a task's status to completed.
     *
     * @param tasks The task list containing the task.
     * @param ui The user interface to display messages.
     * @param storage The storage system (not modified in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(" Nice! I've marked this task as done:\n " + tasks.markTask(index) + "\n");
    }
}
