package task;

import java.util.ArrayList;

import exceptions.InvalidArgumentException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given tasks.
     *
     */
    public TaskList() {
        this.tasks = null;
    }
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a formatted string of all tasks in the list.
     *
     * @return A string representation of the task list.
     */
    public String listTasks() {
        assert tasks != null : "TaskList must be initialised";
        StringBuilder listOfTasks = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String numberedTask = " " + (i + 1) + ". " + tasks.get(i) + "\n";
            listOfTasks.append(numberedTask);
        }
        return listOfTasks.toString();
    }

    /**
     * Searches for tasks that contain the given keyword.
    * @param keywords The keywords to search for.
    * @return A formatted string of matching tasks.
    */
    public String findTasks(String... keywords) {
        StringBuilder matchingTasks = new StringBuilder(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            for (String keyword : keywords) {
                if (tasks.get(i).toString().contains(keyword)) {
                    String matchedTask = " " + (i + 1) + ". " + tasks.get(i) + "\n";
                    matchingTasks.append(matchedTask);
                    break;
                }
            }
        }
        return matchingTasks.toString();
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to be marked.
     * @return The updated task.
     */
    public Task markTask(int index) throws ArrayIndexOutOfBoundsException, InvalidArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        tasks.get(index).mark();
        return tasks.get(index);
    }

    /**
     * Marks a task as not completed.
     *
     * @param index The index of the task to be unmarked.
     * @return The updated task.
     */
    public Task unmarkTask(int index) throws ArrayIndexOutOfBoundsException, InvalidArgumentException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     * @return The new size of the task list.
     */
    public int addTask(Task task) {
        tasks.add(task);
        return tasks.size();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be removed.
     * @return A message confirming the task removal.
     */
    public String deleteTask(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String deleteTaskDescription = tasks.get(index).toString();
        tasks.remove(index);
        return " Noted. I've removed this task:\n" + " " + deleteTaskDescription + "\n" + " Now you have "
                + tasks.size() + " tasks in the list.\n";
    }
    /**
     * Sorts the tasks by date, prioritizing Deadlines and Events.
     * - Deadlines are sorted by due date.
     * - Events are sorted by start date.
     * - ToDo tasks, which do not have a date, are moved to the end.
     */
    public String sortByDate() {
        tasks.sort(new DateComparator());
        return "Tasks have been sorted by date.";
    }
}
