package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.InvalidArgumentException;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param dueDate The due date and time of the task in the format "yyyy-MM-dd HHmm".
     *           Example: "2024-02-06 1530".
     */

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Retyrns a string representation of the Deadline task
     * @return A formatted string with the task type, description, and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                + ")";
    }

    /**
     * Returns a formatted string representation of deadline for saving
     * @return A string formatted for storage purposes
     */
    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | "
                + this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Loads a Deadline task from stored data.
     *
     * @param args The stored data array.
     * @return A Deadline task.
     */
    public static Deadline loadTask(String[] args) throws InvalidArgumentException {
        Deadline deadline = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            deadline.mark();
        }
        return deadline;
    }
    /**
     * Returns the due date of the deadline task.
     *
     * @return The due date as a LocalDateTime object.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
