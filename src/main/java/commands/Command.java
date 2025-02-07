package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage system.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines whether the application should continue running.
     *
     * @return true if the application should continue running, false otherwise.
     */
    public boolean isRunning() {
        return true;
    }
}
