public class Driver implements IDriver{

    @Override
    public void createDatabase(String schemaPath) {

    }

    @Override
    public void set(String databaseName, String jsonObject) {

    }

    @Override
    public String get(String databaseName, String jsonQuery) {
        return null;
    }

    @Override
    public void delete(String databaseName, String jsonQuery) {

    }

    // here we will get the output from the python then pass it to java output function
    // it will take it and check the status
    //      if any exception will call exceptionRaiser and give it the message and exception name
    //      or success and get function return the result
    //      print the message
    private void output(String jsonResult) {

    }
    // will take the exceptionName and throw the proper exception and pass to it the message
    private void exceptionRaiser(String exceptionName, String exceptionMessage) {

    }
    // will take the result of get and print it to the console or website later
    private String result(String result) {
        return null;
    }
}
