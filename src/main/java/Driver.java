import exceptions.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Paths;


public class Driver implements IDriver{
    private final Executer executer;
    public Driver(final Executer executer){
        this.executer = executer;
    }
    @Override
    public void createDatabase(String schemaPath) throws IOException, JSONException {
        String command = String.format("%s -cmd create -sch %s",Paths.get("source","main.py").toString(), schemaPath);
        JSONObject api = executer.execute(command);
        exceptionRaiser(api);
    }

    @Override
    public void set(String databaseName, String tableName, String jsonObject) {

    }

    @Override
    public String get(String databaseName, String tableName, String jsonQuery) {

        return null;
    }

    @Override
    public void delete(String databaseName, String jsonQuery) {

    }
    private void exceptionRaiser(JSONObject api) throws JSONException {
        if (api.getString("status").equals("ColumnsNotExistInSchema"))
            throw new ColumnsNotExistInSchema(api.getString("message"));

        else if (api.getString("status").equals("DatabaseNotExist"))
            throw new DatabaseNotExist(api.getString("message"));

        else if (api.getString("status").equals("FileNotFound"))
            throw new FileNotFound(api.getString("message"));

        else if (api.getString("status").equals("MissingInput"))
            throw new MissingInput(api.getString("message"));

        else if (api.getString("status").equals("RowExists"))
            throw new RowExists(api.getString("message"));

        else if (api.getString("status").equals("TableNotExist"))
            throw new TableNotExist(api.getString("message"));

        else if (api.getString("status").equals("WrongInput"))
            throw new WrongInput(api.getString("message"));
    }
}
