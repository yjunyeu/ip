package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private Storage storage;

    @BeforeEach
    void setup() {
        Ui mockUi = null;
        TaskList mockTasks = new TaskList();
        storage = new Storage();
        storage.setUi(mockUi, mockTasks);
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
