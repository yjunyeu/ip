package task;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setup() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    void testAddTask() {
        ToDo todo = new ToDo("Exercise");
        assertEquals(1, taskList.addTask(todo), "Task list size should be 1 after adding a task");
    }

    @Test
    void testMarkTask() {
        taskList.addTask(new ToDo("Read a book"));
        assertTrue(taskList.markTask(0).toString().contains("[X]"), "Task should be marked");
    }

    @Test
    void testUnmarkTask() {
        taskList.addTask(new ToDo("Read a book"));
        taskList.markTask(0);
        assertTrue(taskList.unmarkTask(0).toString().contains("[ ]"), "Task should be unmarked");
    }

    @Test
    void testDeleteTask() {
        taskList.addTask(new ToDo("Delete me"));
        String result = taskList.deleteTask(0);
        assertTrue(result.contains("removed this task"), "Deleted task message should be returned");
    }
}
