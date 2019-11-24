package myPage;

import java.util.HashMap;

public class DataSource {
    private HashMap<String, String> userSource;

    public DataSource(){
        userSource = new HashMap<>();
    }

    public void addUser(String name, String password){
        userSource.put(name, password);
    }

    public boolean userExist(String name){
        return userSource.containsKey(name);
    }

    public boolean checkPassword(String name, String password){
        String passwd = userSource.get(name);
        if(passwd != null) {
            if (passwd.compareTo(password) == 0)
                return true;
        }
        return false;
    }

}
