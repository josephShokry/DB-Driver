import org.json.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Execute {
    private String databasePath;
    private String pythonCaller;
    public Execute() throws IOException {
        Path filePath = Paths.get(Paths.get(System.getProperty("user.dir")).toString(), "src", "main", "java", "configurations.properties");
        loadConfg(filePath.toString());
    }
    private void loadConfg(String confgFilePath) throws IOException {
        FileReader confgFile = new FileReader(confgFilePath);
        Properties confg = new Properties();
        confg.load(confgFile);
        databasePath = confg.getProperty("databasePath");
        pythonCaller = confg.getProperty("pythonCaller");
    }
    JSONObject execute(String command) throws IOException, JSONException {
        File dir = new File(databasePath);
        Process process = Runtime.getRuntime().exec(pythonCaller + " " + command,null,dir);
        BufferedReader outputMessage = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return new JSONObject(outputMessage.readLine());
    }
}
