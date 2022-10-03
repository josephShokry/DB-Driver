package exceptions;

/**
 * this exception will be thrown if the user trying to get a none existent file.
 */
public class FileNotFound extends RuntimeException {
    /**
     * @param message
     */
    public FileNotFound(final String message) {
        super(message);
    }
}
