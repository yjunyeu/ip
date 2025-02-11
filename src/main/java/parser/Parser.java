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
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
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
            switch (key) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                if (split.length < 2) {
                    throw new InvalidArgumentException("Example usage: mark 1\n");
                }
                int markIndex = Integer.parseInt(split[1].strip()) - 1;
                return new MarkCommand(markIndex);
            case UNMARK:
                if (split.length < 2) {
                    throw new InvalidArgumentException("Example usage: unmark 1\n");
                }
                int unmarkIndex = Integer.parseInt(split[1].strip()) - 1;
                return new UnmarkCommand(unmarkIndex);
            case TODO:
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    throw new InvalidArgumentException("The description of a todo cannot be empty.\n");
                }
                String taskDescription = split[1].strip();
                return new AddTodoCommand(taskDescription);
            case DEADLINE:
                if (split.length < 2 || !split[1].contains("/by")) {
                    throw new InvalidArgumentException(
                            "The deadline task must include a description and a '/by' argument.\n");
                }
                String[] deadlineSplit = split[1].split(" /by", 2);
                return new AddDeadlineCommand(deadlineSplit[0].strip(), deadlineSplit[1].strip());
            case EVENT:
                if (split.length < 2 || !split[1].contains("/from") || !split[1].contains("/to")) {
                    throw new InvalidArgumentException(
                            "The event task must include a description, a '/from' argument, and a '/to' argument.\n");
                }
                String[] eventSplit = split[1].split(" /from ", 2);
                String[] timeSplit = eventSplit[1].split(" /to", 2);
                return new AddEventCommand(eventSplit[0].strip(), timeSplit[0].strip(), timeSplit[1].strip());
            case DELETE:
                if (split.length < 2) {
                    throw new InvalidArgumentException("Example usage: delete 1\n");
                }
                int deleteIndex = Integer.parseInt(split[1].strip()) - 1;
                return new DeleteCommand(deleteIndex);
            case FIND:
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    throw new InvalidArgumentException("Example usage: find book\n");
                }
                String[] keywords = split[1].strip().split("\\s+");
                return new FindCommand(keywords);
            default:
                throw new InvalidCommandException("OOPS!!! What do you mean???\n");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("OOPS!!! What do you mean???\n");
        }
    }
}
