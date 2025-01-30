package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import task.Deadline;
public class AddDeadlineCommand extends Command{

    private final Deadline deadline;

    public AddDeadlineCommand(String description, String by) {
        this.deadline = new Deadline(description, by);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int size = tasks.addTask(deadline);
        ui.displayMessage(" Got it. I've added this task:\n" + " " + deadline + "\n"
                + " Now you have " + size + " tasks in the list.\n");
    }
}
