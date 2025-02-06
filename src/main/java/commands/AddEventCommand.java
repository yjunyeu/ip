package commands;

import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to add an Event task.
 */
public class AddEventCommand extends Command {
    private final Event event;

    /**
     * Constructs an AddEventCommand with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time in the format "yyyy-MM-dd HHmm".
     * @param to The end time in the format "yyyy-MM-dd HHmm".
     */
    public AddEventCommand(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    /**
     * Executes the command by adding an event task to the task list.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface to display messages.
     * @param storage The storage system (not modified in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int size = tasks.addTask(event);
        ui.displayMessage(" Got it. I've added this task:\n" + " " + event + "\n" + " Now you have "
                + size + " tasks in the list.\n");
    }
}
