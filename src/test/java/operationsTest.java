import exceptions.*;
import org.json.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class operationsTest {

    Execute mockedExecute = mock(Execute.class);


    @Test
    void creatTest() throws JSONException, IOException {
        String schemaPath = "C:\\Users\\lenovo\\Desktop\\check_in_system\\DB\\SimpleFSDB\\tests\\testcases_schemas\\schema.txt";
        String command = Paths.get("source","main.py") + " -cmd create -sch " + schemaPath;
        when(mockedExecute.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the command has been executed successfully!', 'status': 'Success'}"));
//        try{
            new Driver().createDatabase(schemaPath);
//        }
//        catch (Exception e){
//            fail("the command raised error");
//        }
    }
    @Test
    void notGivingSchema() throws JSONException, IOException {//not giving the schema or the schema file is not proper
        String schemaPath = "C:\\Users\\lenovo\\Desktop\\check_in_system\\DB\\SimpleFSDB\\tests\\testcases_schemas\\ssssschema.txt";
        when(mockedExecute.execute(Paths.get("source","main.py") + " -cmd create -sch " + schemaPath)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the schema path is not valid', 'status': 'FileNotFound'}"));
        try{
            new Driver().createDatabase("C:\\Users\\lenovo\\Desktop\\check_in_system\\DB\\SimpleFSDB\\tests\\testcases_schemas\\ssssschema.txt");
        }
        catch (Exception e){
            if(!(e instanceof FileNotFound)){
                fail();
            }
        }
    }
}