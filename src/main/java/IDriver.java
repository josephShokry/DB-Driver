import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public interface IDriver {
    /**
     * to call the FSDB to create a new database
     * @param schemaPath the schema of the database required to be created
     */
    void createDatabase (String schemaPath) throws IOException, JSONException;


    /**
     * to set a new row (element) in the database
     * @param databaseName the name of database to set in the new row
     * @param jsonObject the row wanted to be set in the database
     */

    void set (String databaseName, String tableName, String jsonObject) throws JSONException, IOException;

    /**
     * to get a row form the database
     * @param databaseName the name of database to get form it the row
     * @param jsonQuery the query data of required row
     * @return list of json of row that match the query
     */
    JSONArray get (String databaseName, String tableName, String jsonQuery) throws JSONException, IOException;

    /**
     *
     * @param databaseName the name of database to delete form it the row
     * @param jsonQuery the query data of required row
     */
    void delete (String databaseName, String tableName, String jsonQuery) throws JSONException, IOException;

}
