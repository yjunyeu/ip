package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command{

    /**
     * Executes the exit command by saving tasks and displaying a goodbye message.
     *
     * @param tasks The task list (not modified in this command).
     * @param ui The user interface to display messages.
     * @param storage The storage system to save tasks before exiting.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks();
        ui.displayBye();
    }

    /**
     * Indicates that the application should stop running.
     *
     * @return false, signaling the application should exit.
     */
    public boolean isRunning() { return false; }
}
