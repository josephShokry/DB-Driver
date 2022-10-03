package exceptions;

/**
 * this exception will be thrown if the input was not valid.
 */
public class WrongInput extends RuntimeException {
    /**
     * @param message
     */
    public WrongInput(final String message) {
        super(message);
    }
}
