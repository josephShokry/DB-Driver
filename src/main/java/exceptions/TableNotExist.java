package exceptions;

/**
 * this exception will be thrown if the user trying to use a nonexistent table
 */
public class TableNotExist extends RuntimeException {
    TableNotExist(String message) {
        super(message);
    }
}
