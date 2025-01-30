package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks();
        ui.displayBye();
    }
    public boolean isRunning() { return false; }
}
