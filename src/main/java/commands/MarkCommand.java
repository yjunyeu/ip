package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command{
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(" Nice! I've marked this task as done:\n " + tasks.markTask(index) + "\n");
    }
}
