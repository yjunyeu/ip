package task;

import exceptions.InvalidArgumentException;

/**
 * Represents a simple task without a specific deadline or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A formatted string including task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string representation of the task for saving.
     *
     * @return A string formatted for storage purposes.
     */
    @Override
    public String getSaveData() {
        return "T | " + super.getSaveData();
    }

    /**
     * Loads a ToDo task from stored data.
     *
     * @param args The stored data array.
     * @return A ToDo task.
     */
    public static ToDo loadTask(String[] args) throws InvalidArgumentException {
        ToDo todo = new ToDo(args[2]);
        if (args[1].equals("1")) {
            todo.mark();
        }
        return todo;
    }
}
