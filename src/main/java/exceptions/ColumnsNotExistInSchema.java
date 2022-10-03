package exceptions;

/**
 * this exception will be thrown if value has column not in schema of database is being tried to be set in the DB.
 */
public class ColumnsNotExistInSchema extends RuntimeException {
    /**
     * @param message
     */
    public ColumnsNotExistInSchema(final String message) {
        super(message);
    }
}
