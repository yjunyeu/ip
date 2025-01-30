package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import task.Event;
public class AddEventCommand extends Command{
    private final Event event;

    public AddEventCommand(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int size = tasks.addTask(event);
        ui.displayMessage(" Got it. I've added this task:\n" + " " + event + "\n" + " Now you have " +
                size + " tasks in the list.\n");
    }
}
