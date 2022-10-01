import exceptions.*;
import org.json.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Driver implements IDriver{
    private final String databasePath;
    private final String pythonCaller;
    public Driver() throws IOException {
        Path confgFilePath = Paths.get(System.getProperty("user.dir"),"src", "main", "java", "configurations.properties");
        FileReader confgFile = new FileReader(confgFilePath.toString());
        Properties confg = new Properties();
        confg.load(confgFile);
        databasePath = confg.getProperty("databasePath");
        pythonCaller = confg.getProperty("pythonCaller");
    }
    @Override
    public void createDatabase(String schemaPath) throws IOException, JSONException {
        String command = pythonCaller + " " + Paths.get("source","main.py") + " -cmd create -sch " + schemaPath;
        JSONObject api = new Execute().execute(command);
        exceptionRaiser(api);
    }

    @Override
    public void set(String databaseName, String tableName,String jsonObject) throws IOException, JSONException {

    }

    @Override
    public String get(String databaseName, String jsonQuery) {
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
