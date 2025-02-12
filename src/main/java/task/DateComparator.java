package task;

import java.util.Comparator;
/**
 * A comparator that sorts tasks by their associated date.
 * - Deadlines are compared using their due date.
 * - Events are compared using their start date.
 * - ToDo tasks are always placed last since they have no date.
 */
public class DateComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        // Ensure ToDo tasks are always last
        if (t1 instanceof ToDo && !(t2 instanceof ToDo)) {
            return 1; // Move ToDo tasks down
        }
        if (t2 instanceof ToDo && !(t1 instanceof ToDo)) {
            return -1; // Move non-ToDo tasks up
        }

        // Sort Deadlines and Events by their respective dates
        if (t1 instanceof Deadline && t2 instanceof Deadline) {
            return ((Deadline) t1).getDueDate().compareTo(((Deadline) t2).getDueDate());
        }
        if (t1 instanceof Event && t2 instanceof Event) {
            return ((Event) t1).getFromDate().compareTo(((Event) t2).getFromDate());
        }
        if (t1 instanceof Deadline && t2 instanceof Event) {
            return ((Deadline) t1).getDueDate().compareTo(((Event) t2).getFromDate());
        }
        if (t1 instanceof Event && t2 instanceof Deadline) {
            return ((Event) t1).getFromDate().compareTo(((Deadline) t2).getDueDate());
        }

        // If neither have dates, maintain order
        return 0;
    }
}
