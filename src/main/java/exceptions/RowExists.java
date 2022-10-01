package exceptions;

/**
 * this exception will be thrown if the user trying to set a row which is already exists and can't overwrite
 */
public class RowExists extends RuntimeException {
    public RowExists(String message) {
        super(message);
    }
}
