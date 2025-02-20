package parser;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;

import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    void setup() {
        parser = new Parser();
    }

    @Test
    void testParseByeCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    void testParseListCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void testParseMarkCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("mark 2");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void testParseMarkCommandWithoutIndex() {
        Exception exception = assertThrows(
                InvalidArgumentException.class,
                () -> parser.parse("mark")
        );
        assertEquals("Example usage: mark 1\n", exception.getMessage());
    }

    @Test
    void testParseUnmarkCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    void testParseUnmarkCommandWithoutIndex() {
        Exception exception = assertThrows(
                InvalidArgumentException.class,
                () -> parser.parse("unmark")
        );
        assertEquals("Example usage: unmark 1\n", exception.getMessage());
    }

    @Test
    void testParseTodoCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("todo Read book");
        assertTrue(command instanceof AddTodoCommand);
    }

    @Test
    void testParseTodoCommandWithoutDescription() {
        Exception exception = assertThrows(
                InvalidArgumentException.class,
                () -> parser.parse("todo")
        );
        assertEquals("The description of a todo cannot be empty.\n", exception.getMessage());
    }

    @Test
    void testParseDeadlineCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("deadline Submit report /by 2025-02-20 1800");
        assertTrue(command instanceof AddDeadlineCommand);
    }

    @Test
    void testParseDeadlineCommandWithoutBy() {
        Exception exception = assertThrows(
                InvalidArgumentException.class,
                () -> parser.parse("deadline Submit report")
        );
        assertEquals("The deadline task must contain a '/by' argument.\n", exception.getMessage());
    }

    @Test
    void testParseEventCommand() throws InvalidCommandException, InvalidArgumentException {
        Command command = parser.parse("event Team meeting /from 2025-03-05 1400 /to 2025-03-05 1600");
        assertTrue(command instanceof AddEventCommand);
    }
}


