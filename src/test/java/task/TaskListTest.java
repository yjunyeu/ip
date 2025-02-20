package task;

import exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setup() {
        taskList = new TaskList();
        taskList.setTasks(new ArrayList<Task>());
    }

    @Test
    void testAddTask() {
        ToDo todo = new ToDo("Exercise");
        assertEquals(1, taskList.addTask(todo), "Task list size should be 1 after adding a task");
    }

    @Test
    void testMarkTask() {
        taskList.addTask(new ToDo("Read a book"));
        try {
            assertTrue(taskList.markTask(0).toString().contains("[X]"), "Task should be marked");
        } catch (InvalidArgumentException e) {
            fail("Exception should not have been thrown when marking a task");
        }
    }

    @Test
    void testUnmarkTask() {
        taskList.addTask(new ToDo("Read a book"));
        try {
            taskList.markTask(0);
            assertTrue(taskList.unmarkTask(0).toString().contains("[ ]"), "Task should be unmarked");
        } catch (InvalidArgumentException e) {
            fail("Exeption should not have been thrown when unmarking a task");
        }
    }

    @Test
    void testDeleteTask() {
        taskList.addTask(new ToDo("Delete me"));
        String result = taskList.deleteTask(0);
        assertTrue(result.contains("removed this task"), "Deleted task message should be returned");
    }
}
