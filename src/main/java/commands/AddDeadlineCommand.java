package commands;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import task.Deadline;

/**
 * Represents a command to add a Deadline task.
 */
public class AddDeadlineCommand extends Command{

    private final Deadline deadline;

    /**
     * Constructs an AddDeadlineCommand with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date and time in the format "yyyy-MM-dd HHmm".
     */
    public AddDeadlineCommand(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    /**
     * Executes the command by adding a deadline task to the task list.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface to display messages.
     * @param storage The storage system (not modified in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int size = tasks.addTask(deadline);
        ui.displayMessage(" Got it. I've added this task:\n" + " " + deadline.toString() + "\n" + " Now you have " +
                String.valueOf(size) + " tasks in the list.\n");
    }
}
