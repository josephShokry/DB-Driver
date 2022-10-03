import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class Executer {
    private final String databasePath;
    private final String pythonCaller;
    private static Executer executerObject = null;

    private Executer() throws IOException {
        Path filePath = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
                "src", "main", "java", "configurations.properties");
        Properties confg = loadConfg(filePath.toString());
        this.databasePath = confg.getProperty("databasePath");
        this.pythonCaller = confg.getProperty("pythonCaller");

    }

    /**
     * @return executerObject
     * @throws IOException
     */
    public static Executer getInstance() throws IOException {
        if (executerObject == null) {
            executerObject = new Executer();
        }
        return executerObject;
    }

    private Properties loadConfg(final String confgFilePath) throws IOException {
        final FileReader confgFile = new FileReader(confgFilePath);
        final Properties confg = new Properties();
        confg.load(confgFile);
        confgFile.close();
        return confg;
    }

    JSONObject execute(final String command) throws IOException, JSONException {
        final File dir = new File(this.databasePath);
        final Process process = Runtime.getRuntime().exec(
                pythonCaller + " " + command, null, dir);
        final BufferedReader outputMessage = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return new JSONObject(outputMessage.readLine());
    }
}
