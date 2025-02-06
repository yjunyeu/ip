package exceptions;

/**
 * Represents an exception that is thrown when an invalid command is entered.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs an InvalidCommandException with the specified error message.
     *
     * @param message The error message describing the invalid command.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
