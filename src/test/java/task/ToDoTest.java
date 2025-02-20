package task;

import exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    void testToDoStringOutput() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString());

        try {
            todo.mark();
            assertEquals("[T][X] Read a book", todo.toString());
        } catch (InvalidArgumentException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testSaveData() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("T | 0 | Buy groceries", todo.getSaveData());
    }
}
