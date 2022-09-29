package exceptions;

/**
 * this exception will be thrown if the user trying to use a nonexistent database
 */
public class DatabaseNotExist extends RuntimeException {
    DatabaseNotExist(String message) {
        super(message);
    }
}
