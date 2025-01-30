package task;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public String listTasks() {
        StringBuilder listOfTasks = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String numberedTask = " " + String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
            listOfTasks.append(numberedTask);
        }
        return listOfTasks.toString();
    }

    /**
     * Searches for tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A formatted string of matching tasks.
     */
    public String findTasks(String keyword) {
        StringBuilder matchingTasks = new StringBuilder(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                String matchedTask = " " + String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
                matchingTasks.append(matchedTask);
            }
        }
        return matchingTasks.toString();
    }

    public Task markTask(int index) {
        tasks.get(index).mark();
        return tasks.get(index);
    }

    public Task unmarkTask(int index) {
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    public int addTask(Task task) {
        tasks.add(task);
        return tasks.size();
    }

    public String deleteTask(int index) {
        String deleteTaskDescription = tasks.get(index).toString();
        tasks.remove(index);
        return " Noted. I've removed this task:\n" + " " + deleteTaskDescription + "\n" + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }
}
