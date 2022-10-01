import org.json.JSONException;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, JSONException {
        try{
            Driver driver = new Driver();
            driver.createDatabase("C:\\Users\\lenovo\\Desktop\\check_in_system\\DB\\SimpleFSDB\\tests\\testcases_schemas\\schema.txt");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
