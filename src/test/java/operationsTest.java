import exceptions.*;
import org.json.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class operationsTest {

    @Test
    void creatTest() throws JSONException, IOException {
        Executer mockedExecuter = mock(Executer.class);
        String schemaPath = "C:\\Users\\lenovo\\Desktop\\check_in_system\\DB\\SimpleFSDB\\tests\\testcases_schemas\\schema.txt";
        String command = Paths.get("source","main.py") + " -cmd create -sch " + schemaPath;
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the command has been executed successfully!', 'status': 'Success'}"));
        try{
            new Driver(mockedExecuter).createDatabase(schemaPath);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
    }
    @Test
    void setfunction() throws JSONException, IOException {//testing the set function
        Executer mockedExecuter = mock(Executer.class);
        String studentObj = "{'id': '30','first_name': 'joseph','last_name': 'shokry','age': '20','gender': 'male'}";
        String command = String.format("%s -cmd set -database csed2025 -table Students -val \"%s\"",Paths.get("source","main.py").toString(),studentObj);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the command has been executed successfully!', 'status': 'Success'}"));
        new Driver(mockedExecuter).set("csed2025","Students", studentObj);
    }
    @Test
    void getfunction() throws JSONException, IOException {//testing the get function
        JSONArray get = new JSONArray("[{'id': '30', 'first_name': 'joseph', 'last_name': 'shokry', 'age': '20', 'gender': 'male'}]");
        JSONObject apiExpected = new JSONObject().put("result",get);
        apiExpected.put("message","the command has been executed successfully!").put("status","Success");
        Executer mockedExecuter = mock(Executer.class);
        String getQuery = "{'id': '30'}";
        String command = String.format("%s -cmd get -database csed2025 -table Students -q \"%s\"",Paths.get("source","main.py").toString(),getQuery);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': \"[{'id': '30', 'first_name': 'joseph', 'last_name': 'shokry', 'age': '20', 'gender': 'male'}]\", 'message': 'the command has been executed successfully!', 'status': 'Success'}"));
        JSONArray res = new Driver(mockedExecuter).get("csed2025","Students", getQuery);
    }

    @Test
    void deletefunction() throws JSONException, IOException {//testing the delete function
        Executer mockedExecuter = mock(Executer.class);
        String getQuery = "{'id': '30'}";
        String command = String.format("%s -cmd delete -database csed2025 -table Students -q \"%s\"",Paths.get("source","main.py").toString(),getQuery);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the command has been executed successfully!', 'status': 'Success'}"));
        new Driver(mockedExecuter).delete("csed2025","Students", getQuery);
    }

    @Test
    void notGivingSchema() throws JSONException, IOException {//not giving the schema or the schema file is not proper
        Executer mockedExecuter = mock(Executer.class);
        String schemaPath = "C:\\Users\\lenovo\\Desktop\\check_in_system\\DB\\SimpleFSDB\\tests\\testcases_schemas\\ssssschema.txt";
        String command = Paths.get("source","main.py") + " -cmd create -sch " + schemaPath;
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the schema path is not valid', 'status': 'FileNotFound'}"));
        try{
            new Driver(mockedExecuter).createDatabase(schemaPath);
        }
        catch (Exception e){
            if(!(e instanceof FileNotFound)){
                fail();
            }
        }
    }
    @Test
    void wrongColomnsSet() throws JSONException, IOException {//testing the set function not giving
        Executer mockedExecuter = mock(Executer.class);
        String studentObj = "{'id': '30','f_name': 'joseph','last_name': 'shokry','age': '20','gender': 'male'}";
        String command = String.format("%s -cmd set -database csed2025 -table Students -val \"%s\"",Paths.get("source","main.py").toString(),studentObj);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'f_name is not exist in the schema of Students table', 'status': 'ColumnsNotExistInSchema'}"));
        try{
            new Driver(mockedExecuter).set("csed2025","Students", studentObj);
        }
        catch(Exception e){
            if(!(e instanceof ColumnsNotExistInSchema))
                fail();
        }
    }

    @Test
    void setInNotExistDB() throws JSONException, IOException {//testing the set function set in none exists DB
        Executer mockedExecuter = mock(Executer.class);
        String studentObj = "{'id': '30','first_name': 'joseph','last_name': 'shokry','age': '20','gender': 'male'}";
        String command = String.format("%s -cmd set -database cccccsed2025 -table Students -val \"%s\"",Paths.get("source","main.py").toString(),studentObj);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the database name you entered is not valid or database is not exist', 'status': 'DatabaseNotExist'}"));
        try{
            new Driver(mockedExecuter).set("cccccsed2025","Students", studentObj);
        }
        catch(Exception e){
            System.out.println(e);
            if(!(e instanceof DatabaseNotExist))
                fail();
        }
    }
    @Test
    void setInNotExistTable() throws JSONException, IOException {//testing the set function set in none exists table
        Executer mockedExecuter = mock(Executer.class);
        String studentObj = "{'id': '30','first_name': 'joseph','last_name': 'shokry','age': '20','gender': 'male'}";
        String command = String.format("%s -cmd set -database csed2025 -table Sssssstudents -val \"%s\"",Paths.get("source","main.py").toString(),studentObj);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the table name you entered is not valid or table is not exist', 'status': 'TableNotExist'}"));
        try{
            new Driver(mockedExecuter).set("csed2025","Sssssstudents", studentObj);
        }
        catch(Exception e){
            System.out.println(e);
            if(!(e instanceof TableNotExist))
                fail();
        }
    }
    @Test
    void getWithEmptyQuery() throws JSONException, IOException {//testing the get function the query is empty it should get all the data of the table
        Executer mockedExecuter = mock(Executer.class);
        String getQuery = "{}";
        String command = String.format("%s -cmd delete -database csed2025 -table Students -q \"%s\"",Paths.get("source","main.py").toString(),getQuery);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the command has been executed successfully!', 'status': 'Success'}"));
        try{
            new Driver(mockedExecuter).delete("csed2025","Students", getQuery);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    void deleteWithWrongColomn() throws JSONException, IOException {//testing the delete function the query has wrong coloumn
        Executer mockedExecuter = mock(Executer.class);
        String deleteQuery = "{'id': '30','l_name': 'ali'}";
        String command = String.format("%s -cmd delete -database csed2025 -table Students -q \"%s\"",Paths.get("source","main.py").toString(),deleteQuery);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'l_name is not exist in the schema of Students table', 'status': 'ColumnsNotExistInSchema'}"));
        try{
            new Driver(mockedExecuter).delete("csed2025","Students", deleteQuery);
        }
        catch (Exception e){
            if(!(e instanceof ColumnsNotExistInSchema))
                fail();
        }
    }
    @Test
    void deleteWithEmptyQuery() throws JSONException, IOException {//testing the delete function the query is empty it should delete all the data of the table
        Executer mockedExecuter = mock(Executer.class);
        String deleteQuery = "{}";
        String command = String.format("%s -cmd delete -database csed2025 -table Students -q \"%s\"",Paths.get("source","main.py").toString(),deleteQuery);
        when(mockedExecuter.execute(command)).thenReturn(
                new JSONObject("{'result': 'None', 'message': 'the command has been executed successfully!', 'status': 'Success'}"));
        try{
            new Driver(mockedExecuter).delete("csed2025","Students", deleteQuery);
        }
        catch (Exception e){
                fail();
        }
    }
}