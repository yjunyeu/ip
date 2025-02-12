package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event task with a specific start and end time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task.
     *
     * @param description The description of the event.
     * @param from The start time in the format "yyyy-MM-dd HHmm".
     * @param to The end time in the format "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string including task type, description, and time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")) + ")";
    }

    /**
     * Returns a formatted string representation of event for saving
     * @return A string formatted for storage purposes
     */
    @Override
    public String getSaveData() {
        return "E | " + super.getSaveData() + " | " + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + "-" + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Loads an Event task from stored data.
     *
     * @param args The stored data array.
     * @return An Event task.
     */
    public static Event loadTask(String[] args) {
        String[] period = args[3].split("-");
        Event event = new Event(args[2], period[0], period[1]);
        if (args[1].equals("1")) {
            event.mark();
        }
        return event;
    }

    /**
     * Returns the start date of the event.
     * @return The start date as a LocalDateTime object.
     */
    public LocalDateTime getFromDate() {
        return from;
    }
}
