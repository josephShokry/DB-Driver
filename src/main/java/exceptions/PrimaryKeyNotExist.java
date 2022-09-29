package exceptions;

public class PrimaryKeyNotExist extends Exception{
    PrimaryKeyNotExist(String message){
        super(message);
    }
}
