package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void testDeadlineStringOutput() {
        Deadline deadline = new Deadline("Submit assignment", "2025-02-10 1800");
        assertEquals("[D][ ] Submit assignment (by: 10 Feb 2025 1800)", deadline.toString());

        deadline.mark();
        assertEquals("[D][X] Submit assignment (by: 10 Feb 2025 1800)", deadline.toString());
    }

    @Test
    void testSaveData() {
        Deadline deadline = new Deadline("Project Deadline", "2025-03-01 2359");
        assertEquals("D | 0 | Project Deadline | 2025-03-01 2359", deadline.getSaveData());
    }
}
