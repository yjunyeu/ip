package task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    void testMarkUnmarkTask() {
        Task task = new ToDo("Test Task");
        assertFalse(task.toString().contains("[X]"), "Task should be unmarked initially");

        task.mark();
        assertTrue(task.toString().contains("[X]"), "Task should be marked after calling mark()");

        task.unmark();
        assertFalse(task.toString().contains("[X]"), "Task should be unmarked after calling unmark()");
    }

    @Test
    void testGetSaveData() {
        Task task = new ToDo("Save Task");
        assertEquals("T | 0 | Save Task", task.getSaveData());

        task.mark();
        assertEquals("T | 1 | Save Task", task.getSaveData());
    }
}
