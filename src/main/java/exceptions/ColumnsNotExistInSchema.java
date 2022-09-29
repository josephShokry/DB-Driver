package exceptions;

public class ColumnsNotExistInSchema extends Exception {
    ColumnsNotExistInSchema(String message){
        super(message);
    }
}
