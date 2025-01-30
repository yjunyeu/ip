package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks that match a keyword.
 */
public class FindCommand extends Command{
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for matching tasks.
     *
     * @param tasks The task list to search within.
     * @param ui The user interface to display messages.
     * @param storage The storage system (not modified in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String results = tasks.findTasks(keyword);
        ui.displayMessage(results);
    }
}
