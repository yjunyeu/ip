package parser;
import commands.*;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import task.ToDo;
import task.Deadline;
import task.Event;
public class Parser {
    private enum Key {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    }
    public Parser() {}
    public Command parse(String userInput) throws InvalidArgumentException, InvalidCommandException {
        String[] split = userInput.split("\\s+", 2);
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
                    if (split.length < 2 || !split[1].contains("/by"))
                        throw new InvalidArgumentException(
                                "The deadline task must include a description and a '/by' argument.\n");
                    String[] deadlineSplit = split[1].split(" /by", 2);
                    return new AddDeadlineCommand(deadlineSplit[0].strip(), deadlineSplit[1].strip());
                case EVENT:
                    if (split.length < 2 || !split[1].contains("/from") || !split[1].contains("/to"))
                        throw new InvalidArgumentException(
                                "The event task must include a description, a '/from' argument, and a '/to' argument.\n");
                    String[] eventSplit = split[1].split(" /from ", 2);
                    String[] timeSplit = eventSplit[1].split(" /to", 2);
                    return new AddEventCommand(eventSplit[0].strip(), timeSplit[0].strip(), timeSplit[1].strip());
                case DELETE:
                    if (split.length < 2) {
                        throw new InvalidArgumentException("Example usage: delete 1\n");
                    }
                    int deleteIndex = Integer.parseInt(split[1].strip()) - 1;
                    return new DeleteCommand(deleteIndex);
                default:
                    throw new InvalidCommandException("OOPS!!! What do you mean???\n");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("OOPS!!! What do you mean???\n");
        }
    }
}
