package exceptions;

/**
 * this exception will be thrown if the user trying to get a none existent file
 */
public class FileNotFound extends RuntimeException {
    FileNotFound(String message) {
        super(message);
    }
}
