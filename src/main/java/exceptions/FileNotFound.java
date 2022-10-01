package exceptions;

/**
 * this exception will be thrown if the user trying to get a none existent file
 */
public class FileNotFound extends RuntimeException {
    public FileNotFound(String message) {
        super(message);
    }
}
