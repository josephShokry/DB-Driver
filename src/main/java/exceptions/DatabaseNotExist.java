package exceptions;

public class DatabaseNotExist extends Exception{
    DatabaseNotExist(String message){
        super(message);
    }
}
