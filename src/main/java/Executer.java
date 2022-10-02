import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class Executer {
    private String databasePath;
    private String pythonCaller;
    private static Executer executerObject = null;
    private Executer() throws IOException {
        Path filePath = Paths.get(Paths.get(System.getProperty("user.dir")).toString(), "src", "main", "java", "configurations.properties");
        loadConfg(filePath.toString());
    }
    public static Executer getInstance() throws IOException {
        if(executerObject == null){
            executerObject = new Executer();
        }
        return executerObject;
    }
    private void loadConfg(String confgFilePath) throws IOException {
        final FileReader confgFile = new FileReader(confgFilePath);
        final Properties confg = new Properties();
        confg.load(confgFile);
        databasePath = confg.getProperty("databasePath");
        pythonCaller = confg.getProperty("pythonCaller");
    }
    JSONObject execute(String command) throws IOException, JSONException {
        final File dir = new File(databasePath);
        final Process process = Runtime.getRuntime().exec(pythonCaller + " " + command,null,dir);
        final BufferedReader outputMessage = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return new JSONObject(outputMessage.readLine());
    }
}
