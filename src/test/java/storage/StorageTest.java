package storage;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import ui.Ui;
import java.util.ArrayList;

public class StorageTest {
    private Storage storage;

    @BeforeEach
    void setup() {
        Ui mockUi = new Ui();
        storage = new Storage(mockUi);
    }

    @Test
    void testSaveTasks() {
        storage.saveTasks(); // Ensure no exceptions occur
        assertTrue(true, "Saving should complete without exceptions");
    }

    @Test
    void testLoadTasks() {
        ArrayList<Task> tasks = storage.loadTasks();
        assertNotNull(tasks, "Loaded task list should not be null");
    }
}
