package exceptions;

/**
 * this exception will be thrown
 * if the user trying to set a row which is already exists and can't overwrite.
 */
public class RowExists extends RuntimeException {
    /**
     * @param message
     */
    public RowExists(final String message) {
        super(message);
    }
}
