import com.google.common.base.Strings;

public class operations {
    private String  userName;
    public operations(String name) {
        if(!Strings.isNullOrEmpty(name)) {
            userName = name;
        }
        else
            userName="standard";
    }

    int add(int x, int y){
        return x+y;
    }

    int sub(int x,int y){
        return x-y;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
