import exceptions.ColumnsNotExistInSchema;
import exceptions.DatabaseNotExist;
import exceptions.FileNotFound;
import exceptions.MissingInput;
import exceptions.RowExists;
import exceptions.TableNotExist;
import exceptions.WrongInput;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Paths;


public class Driver implements IDriver {
    /**
     * singleton design pattern.
     */
    private final Executer executer;

    /**
     * @param executer the executer obj
     */
    public Driver(final Executer executer) {
        this.executer = executer;
    }

    /**
     * @param schemaPath the schema of the database required to be created
     * @throws IOException
     * @throws JSONException
     */
    @Override
    public void createDatabase(final String schemaPath)
            throws IOException, JSONException {
        String command = String.format("%s -cmd create -sch %s",
                Paths.get("source", "main.py").toString(), schemaPath);
        JSONObject api = executer.execute(command);
        exceptionRaiser(api);
    }

    /**
     * @param databaseName the name of database to set in the new row
     * @param tableName
     * @param jsonObject   the row wanted to be set in the database
     * @throws JSONException
     * @throws IOException
     */
    @Override
    public void set(final String databaseName,
                    final String tableName,
                    final String jsonObject) throws JSONException, IOException {
        String command = String.format(
                "%s -cmd set -database %s -table %s -val \"%s\"",
                Paths.get("source", "main.py").toString(),
                databaseName, tableName, jsonObject);
        JSONObject api = executer.execute(command);
        exceptionRaiser(api);
    }

    /**
     * @param databaseName the name of database to get form it the row
     * @param tableName
     * @param jsonQuery    the query data of required row
     * @return
     * @throws JSONException
     * @throws IOException
     */
    @Override
    public JSONArray get(final String databaseName,
                         final String tableName,
                         final String jsonQuery)
            throws JSONException, IOException {
        String command = String.format(
                "%s -cmd get -database %s -table %s -q \"%s\"",
                Paths.get("source", "main.py").toString(),
                databaseName, tableName, jsonQuery.toString());
        JSONObject api = executer.execute(command);
        exceptionRaiser(api);
        String result = api.getString("result").replace(
                "{", "\"{").replace("}", "}\"");
        return new JSONArray(result);
    }

    /**
     * @param databaseName the name of database to delete form it the row
     * @param tableName
     * @param jsonQuery    the query data of required row
     * @throws JSONException
     * @throws IOException
     */
    @Override
    public void delete(final String databaseName,
                       final String tableName,
                       final String jsonQuery)
            throws JSONException, IOException {
        final String command = String.format(
                "%s -cmd delete -database %s -table %s -q \"%s\"",
                Paths.get("source", "main.py").toString(),
                databaseName, tableName, jsonQuery.toString());
        JSONObject api = executer.execute(command);
        exceptionRaiser(api);
    }

    /**
     * @param api
     * @throws JSONException
     */
    private void exceptionRaiser(final JSONObject api) throws JSONException {
        if (api.getString("status").equals("ColumnsNotExistInSchema")) {
            throw new ColumnsNotExistInSchema(api.getString("message"));
        } else if (api.getString("status").equals("DatabaseNotExist")) {
            throw new DatabaseNotExist(api.getString("message"));
        } else if (api.getString("status").equals("FileNotFound")) {
            throw new FileNotFound(api.getString("message"));
        } else if (api.getString("status").equals("MissingInput")) {
            throw new MissingInput(api.getString("message"));
        } else if (api.getString("status").equals("RowExists")) {
            throw new RowExists(api.getString("message"));
        } else if (api.getString("status").equals("TableNotExist")) {
            throw new TableNotExist(api.getString("message"));
        } else if (api.getString("status").equals("WrongInput")) {
            throw new WrongInput(api.getString("message"));
        }
    }
}
