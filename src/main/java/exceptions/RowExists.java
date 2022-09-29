package exceptions;

public class RowExists extends Exception{
    RowExists(String message){
        super(message);
    }
}
