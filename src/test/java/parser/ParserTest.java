package parser;
import commands.*;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ParserTest {
    private Parser parser;

    @BeforeEach
    void setup() {
        parser = new Parser();
    }

    // Test valid "bye" command
    @Test
    void testParseByeCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    // Test valid "list" command
    @Test
    void testParseListCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    // Test valid "mark" command
    @Test
    void testParseMarkCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("mark 2");
        assertTrue(command instanceof MarkCommand);
    }

    // Test invalid "mark" command (missing index)
    @Test
    void testParseMarkCommandWithoutIndex() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> parser.parse("mark"));
        assertEquals("Example usage: mark 1\n", exception.getMessage());
    }

    // Test valid "unmark" command
    @Test
    void testParseUnmarkCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    // Test invalid "unmark" command (missing index)
    @Test
    void testParseUnmarkCommandWithoutIndex() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> parser.parse("unmark"));
        assertEquals("Example usage: unmark 1\n", exception.getMessage());
    }

    // Test valid "todo" command
    @Test
    void testParseTodoCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("todo Read book");
        assertTrue(command instanceof AddTodoCommand);
    }

    // Test invalid "todo" command (empty description)
    @Test
    void testParseTodoCommandWithoutDescription() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> parser.parse("todo"));
        assertEquals("The description of a todo cannot be empty.\n", exception.getMessage());
    }

    // Test valid "deadline" command
    @Test
    void testParseDeadlineCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("deadline Submit report /by 2025-02-20 1800");
        assertTrue(command instanceof AddDeadlineCommand);
    }

    // Test invalid "deadline" command (missing /by)
    @Test
    void testParseDeadlineCommandWithoutBy() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> parser.parse("deadline Submit report"));
        assertEquals("The deadline task must include a description and a '/by' argument.\n", exception.getMessage());
    }

    // Test valid "event" command
    @Test
    void testParseEventCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("event Team meeting /from 2025-03-05 1400 /to 2025-03-05 1600");
        assertTrue(command instanceof AddEventCommand);
    }

    // Test invalid "event" command (missing /from or /to)
    @Test
    void testParseEventCommandWithoutFromOrTo() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> parser.parse("event Team meeting"));
        assertEquals("The event task must include a description, a '/from' argument, and a '/to' argument.\n", exception.getMessage());
    }

    // Test valid "delete" command
    @Test
    void testParseDeleteCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand);
    }

    // Test invalid "delete" command (missing index)
    @Test
    void testParseDeleteCommandWithoutIndex() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> parser.parse("delete"));
        assertEquals("Example usage: delete 1\n", exception.getMessage());
    }

    // Test invalid command
    @Test
    void testParseInvalidCommand() {
        Exception exception = assertThrows(InvalidCommandException.class, () -> parser.parse("unknownCommand"));
        assertEquals("OOPS!!! What do you mean???\n", exception.getMessage());
    }
}
