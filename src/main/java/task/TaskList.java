package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a formatted string of all tasks in the list.
     *
     * @return A string representation of the task list.
     */
    public String listTasks() {
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
        for (String keyword : keywords) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).toString().contains(keyword)) {
                    String matchedTask = " " + (i + 1) + ". " + tasks.get(i) + "\n";
                    matchingTasks.append(matchedTask);
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
    public Task markTask(int index) {
        tasks.get(index).mark();
        return tasks.get(index);
    }

    /**
     * Marks a task as not completed.
     *
     * @param index The index of the task to be unmarked.
     * @return The updated task.
     */
    public Task unmarkTask(int index) {
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
    public String deleteTask(int index) {
        String deleteTaskDescription = tasks.get(index).toString();
        tasks.remove(index);
        return " Noted. I've removed this task:\n" + " " + deleteTaskDescription + "\n" + " Now you have "
                + tasks.size() + " tasks in the list.\n";
    }
}
