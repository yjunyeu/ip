package exceptions;

/**
 * Represents an exception that is thrown when an invalid argument is provided.
 */
public class InvalidArgumentException extends Exception{

    /**
     * Constructs an InvalidArgumentException with the specified error message.
     *
     * @param message The error message describing the invalid argument.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
