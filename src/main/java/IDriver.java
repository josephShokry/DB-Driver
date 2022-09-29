public interface IDriver {
    /**
     * to call the FSDB to create a new database
     * @param schemaPath the schema of the database required to be created
     */
    void createDatabase (String schemaPath);

    /**
     * to set a new row (element) in the database
     * @param databaseName the name of database to set in the new row
     * @param jsonObject the row wanted to be set in the database
     */
    void set (String databaseName, String jsonObject);

    /**
     * to get a row form the database
     * @param databaseName the name of database to get form it the row
     * @param jsonQuery the query data of required row
     * @return list of json of row that match the query
     */
    String get (String databaseName, String jsonQuery);

    /**
     *
     * @param databaseName the name of database to delete form it the row
     * @param jsonQuery the query data of required row
     */
    void delete (String databaseName, String jsonQuery);

    // here we will get the output from the python then pass it to java output function
    // it will take it and check the status
    //      if any exception will call exceptionRaiser and give it the message and exception name
    //      or success and get function return the result
    //      print the message
    //private method
    void output (String jsonResult);

    // will take the exceptionName and throw the proper exception and pass to it the message
    //private method
    void exceptionRaiser (String exceptionName, String exceptionMessage);

    // will take the result of get and print it to the console or website later
    //private method
    String result (String result);
}
