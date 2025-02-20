package parser;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.SortChronoCommand;
import commands.UnmarkCommand;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;

/**
 * Parses user input and translates it into executable commands.
 */
public class Parser {

    /**
     * Enumeration of command keywords.
     */
    private enum Key {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, SORT
    }

    /**
     * Constructs a Parser instance.
     */
    public Parser() {}

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The input string entered by the user.
     * @return The corresponding command.
     * @throws InvalidArgumentException If the command format is incorrect.
     * @throws InvalidCommandException If the command is unrecognized.
     */
    public Command parse(String userInput) throws InvalidArgumentException, InvalidCommandException {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";

        String[] split = userInput.split("\\s+", 2);
        assert split.length > 0 : "Command should not be empty";

        try {
            Key key = Key.valueOf(split[0].toUpperCase());
            return handleCommand(key, split);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("OOPS!!! What do you mean???\n");
        }
    }

    /**
     * Handles the command parsing based on the key.
     */
    private Command handleCommand(Key key, String[] split) throws InvalidArgumentException {
        switch (key) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return handleMark(split);
        case UNMARK:
            return handleUnmark(split);
        case TODO:
            return handleTodo(split);
        case DEADLINE:
            return handleDeadline(split);
        case EVENT:
            return handleEvent(split);
        case DELETE:
            return handleDelete(split);
        case FIND:
            return handleFind(split);
        case SORT:
            return new SortChronoCommand();
        default:
            throw new InvalidArgumentException("OOPS!!! What do you mean???\n");
        }
    }

    private Command handleMark(String[] split) throws InvalidArgumentException {
        if (split.length < 2) {
            throw new InvalidArgumentException("Example usage: mark 1\n");
        }
        int markIndex = Integer.parseInt(split[1].strip()) - 1;
        return new MarkCommand(markIndex);
    }

    private Command handleUnmark(String[] split) throws InvalidArgumentException {
        if (split.length < 2) {
            throw new InvalidArgumentException("Example usage: unmark 1\n");
        }
        int unmarkIndex = Integer.parseInt(split[1].strip()) - 1;
        return new UnmarkCommand(unmarkIndex);
    }

    private Command handleTodo(String[] split) throws InvalidArgumentException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new InvalidArgumentException("The description of a todo cannot be empty.\n");
        }
        return new AddTodoCommand(split[1].strip());
    }

    private Command handleDeadline(String[] split) throws InvalidArgumentException {
        if (split.length < 2 || !split[1].contains("/by")) {
            throw new InvalidArgumentException("The deadline task must contain a '/by' argument.\n");
        }
        String[] deadlineSplit = split[1].split(" /by", 2);
        if (deadlineSplit.length < 2) {
            throw new InvalidArgumentException("The description of a deadline cannot be empty.\n");
        }
        return new AddDeadlineCommand(deadlineSplit[0].strip(), deadlineSplit[1].strip());
    }

    private Command handleEvent(String[] split) throws InvalidArgumentException {
        if (split.length < 2 || !split[1].contains("/from") || !split[1].contains("/to")) {
            throw new InvalidArgumentException(
                    "The event task must include a '/from' argument and a '/to' argument.\n"
            );
        }
        String[] eventSplit = split[1].split(" /from ", 2);
        if (eventSplit.length < 2) {
            throw new InvalidArgumentException("The description of an event cannot be empty.\n");
        }
        String[] timeSplit = eventSplit[1].split(" /to", 2);
        if (timeSplit.length < 2) {
            throw new InvalidArgumentException("The dates cannot be empty.\n");
        }
        return new AddEventCommand(eventSplit[0].strip(), timeSplit[0].strip(), timeSplit[1].strip());
    }

    private Command handleDelete(String[] split) throws InvalidArgumentException {
        if (split.length < 2) {
            throw new InvalidArgumentException("Example usage: delete 1\n");
        }
        int deleteIndex = Integer.parseInt(split[1].strip()) - 1;
        return new DeleteCommand(deleteIndex);
    }

    private Command handleFind(String[] split) throws InvalidArgumentException {
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new InvalidArgumentException("Example usage: find book\n");
        }
        return new FindCommand(split[1].strip().split("\\s+"));
    }
}
