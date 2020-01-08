package myPage.basicObjects;

import myPage.data.dataBase.StatusData;
import myPage.dataSourceDB.DataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class Status_Klienta {

    private LinkedList<StatusData> statusy;
    private DataSource dataSource;

    public Status_Klienta() {
        dataSource = new DataSource();
        statusy = new LinkedList<>();
    }

    public StatusData statusyPop() {
        return statusy.pop();
    }

    public boolean statusyEmpty() {
        return statusy.isEmpty();
    }

    public void getStatusy() throws SQLException {
        statusy = dataSource.getStatusy();
    }

    public StatusData getStatusyID(int ID) throws SQLException {
        return dataSource.getStatusyID(ID);
    }

    public void addStatus(HashMap<String, String> parameters) throws SQLException {
        dataSource.addStatusDB(parameters);
    }

    public int countStatus(int id) throws SQLException {
        int a = dataSource.countStatus(id);
        return a;
    }

    public void editStatus(HashMap<String, String> parameters) throws SQLException {
        dataSource.editStatusDB(parameters);
    }
}
