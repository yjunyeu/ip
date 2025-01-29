package commands;
import storage.Storage;
import task.TaskList;
import ui.Ui;
public class UnmarkCommand extends Command{
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(" OK, I've marked this task as not done yet:\n " + tasks.unmarkTask(index) + "\n");
    }
}
