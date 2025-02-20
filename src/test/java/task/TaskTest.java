package task;

import exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    void testMarkUnmarkTask() {
        try {
            Task task = new ToDo("Test Task");
            assertFalse(task.toString().contains("[X]"), "Task should be unmarked initially");

            task.mark();
            assertTrue(task.toString().contains("[X]"), "Task should be marked after calling mark()");

            task.unmark();
            assertFalse(task.toString().contains("[X]"), "Task should be unmarked after calling unmark()");
        } catch (InvalidArgumentException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testGetSaveData() {
        Task task = new ToDo("Save Task");
        assertEquals("T | 0 | Save Task", task.getSaveData());
        try {
            task.mark();
            assertEquals("T | 1 | Save Task", task.getSaveData());
        } catch (InvalidArgumentException e) {
            fail("Exception should not have been thrown");
        }
    }
}
