package exceptions;

/**
 * this exception will be thrown if the input was not valid
 */
public class WrongInput extends RuntimeException {
    WrongInput(String message) {
        super(message);
    }
}
