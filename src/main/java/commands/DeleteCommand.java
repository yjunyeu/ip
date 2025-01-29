package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command{
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(tasks.deleteTask(index));
    }
}
