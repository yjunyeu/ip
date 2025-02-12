package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;
/**
 * Command to sort tasks chronologically.
 */
public class SortChronoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String results = tasks.sortByDate();
        ui.displayMessage(results);
    }
}
