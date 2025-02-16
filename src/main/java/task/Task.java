package task;

/**
 * Represents an abstract task that can be marked done or left unmarked.
 */
public abstract class Task {
    private boolean isDone;
    private final String description;

    /**
     * Constructs a Task with a given description.
     *
     * @param description The task description.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Returns a formatted string representation of the task for saving.
     *
     * @return A string formatted for storage purposes.
     */
    public String getSaveData() {
        return (this.isDone ? "1 | " : "0 | ") + this.description;
    }

    /**
     * Throws an error as an abstract task cannot be loaded.
     *
     * @param args The stored data array.
     * @throws UnsupportedOperationException Always thrown because an abstract Task cannot be loaded.
     */
    public static Task loadTask(String[] args) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Cannot load a abstract task.Task");
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A formatted string including the completion status and task description.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
