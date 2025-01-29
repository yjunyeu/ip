package commands;
import storage.Storage;
import task.TaskList;
import ui.Ui;
public class ListCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(tasks.listTasks());
    }
}
