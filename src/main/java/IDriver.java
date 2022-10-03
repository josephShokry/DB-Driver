import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;


public interface IDriver {
    /**
     * to call the FSDB to create a new database.
     *
     * @param schemaPath the schema of the database required to be created
     */
    void createDatabase(String schemaPath) throws IOException, JSONException;


    /**
     * @param databaseName
     * @param tableName
     * @param jsonObject
     * @throws JSONException
     * @throws IOException
     */

    void set(String databaseName,
             String tableName,
             String jsonObject)
            throws JSONException, IOException;

    /**
     * @param databaseName
     * @param tableName
     * @param jsonQuery
     * @return output json array
     * @throws JSONException
     * @throws IOException
     */
    JSONArray get(String databaseName,
                  String tableName,
                  String jsonQuery)
            throws JSONException, IOException;

    /**
     * @param databaseName
     * @param tableName
     * @param jsonQuery
     * @throws JSONException
     * @throws IOException
     */
    void delete(String databaseName,
                String tableName,
                String jsonQuery)
            throws JSONException, IOException;

}
