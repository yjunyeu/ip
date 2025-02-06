package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void testToDoStringOutput() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString());

        todo.mark();
        assertEquals("[T][X] Read a book", todo.toString());
    }

    @Test
    void testSaveData() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("T | 0 | Buy groceries", todo.getSaveData());
    }
}
