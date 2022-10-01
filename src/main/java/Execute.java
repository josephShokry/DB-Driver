import org.json.*;
import java.io.*;
import java.util.Properties;

public class Execute {
    private final String databasePath;
    private final String pythonCaller;
    public Execute() throws IOException {
        FileReader confgFile = new FileReader("C:\\Users\\lenovo\\Desktop\\DB-driver\\DB-Driver\\src\\main\\java\\configurations.properties");

        Properties confg = new Properties();
        confg.load(confgFile);
        databasePath = confg.getProperty("databasePath");
        pythonCaller = confg.getProperty("pythonCaller");
    }
    JSONObject execute(String command) throws IOException, JSONException {
        File dir = new File(databasePath);
        Process process = Runtime.getRuntime().exec(command,null,dir);
        BufferedReader outputMessage = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return new JSONObject(outputMessage.readLine());
    }
}
