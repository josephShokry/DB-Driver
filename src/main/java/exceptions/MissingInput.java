package exceptions;

/**
 * this exception will be thrown if any of the required inputs is missed
 */
public class MissingInput extends RuntimeException {
    public MissingInput(String message) {
        super(message);
    }
}
