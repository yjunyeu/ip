package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date and time of the task in the format "yyyy-MM-dd HHmm".
     *           Example: "2024-02-06 1530".
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Retyrns a string representation of the Deadline task
     * @return A formatted string with the task type, description, and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                + ")";
    }

    /**
     * Returns a formatted string representation of deadline for saving
     * @return A string formatted for storage purposes
     */
    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Loads a Deadline task from stored data.
     *
     * @param args The stored data array.
     * @return A Deadline task.
     */
    public static Deadline loadTask(String[] args) {
        Deadline deadline = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            deadline.mark();
        }
        return deadline;
    }
}
