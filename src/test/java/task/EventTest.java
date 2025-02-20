package task;

import exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testEventStringOutput() {
        Event event = new Event("Meeting", "2025-03-05 1400", "2025-03-05 1600");
        assertEquals("[E][ ] Meeting (from: 05 Mar 2025 1400 to: 05 Mar 2025 1600)", event.toString());
        try {
            event.mark();
        } catch (InvalidArgumentException e) {
            System.out.println("Test failed");
        }
        assertEquals("[E][X] Meeting (from: 05 Mar 2025 1400 to: 05 Mar 2025 1600)", event.toString());
    }

    @Test
    void testSaveData() {
        Event event = new Event("Conference", "2025-04-10 0900", "2025-04-10 1700");
        assertEquals("E | 0 | Conference | 2025-04-10 0900<>2025-04-10 1700", event.getSaveData());
    }
}
