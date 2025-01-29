package commands;
import storage.Storage;
import task.TaskList;
import ui.Ui;
abstract public class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isRunning() { return true; }
}
