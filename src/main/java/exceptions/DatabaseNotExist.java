package exceptions;

/**
 * this exception will be thrown if the user trying to use a nonexistent database
 */
public class DatabaseNotExist extends RuntimeException {
    public DatabaseNotExist(String message) {
        super(message);
    }
}
