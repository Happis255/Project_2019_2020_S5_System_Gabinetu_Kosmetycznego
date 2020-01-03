package myPage.basicObjects;

import myPage.data.dataBase.UslugaData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Usluga {

    private LinkedList<UslugaData> uslugi;
    private DataSource dataSource;

    public Usluga() {
        dataSource = new DataSource();
        uslugi = new LinkedList<>();
    }

    public UslugaData uslugaPop(){
        return uslugi.pop();
    }

    public boolean uslugiEmpty(){
        return uslugi.isEmpty();
    }

    public int get_MaxId() throws SQLException, DBReadWriteException {
        return dataSource.getMaxIDServiceDB();
    }

    public void register(HashMap<String, String> parameters) throws SQLException, ParseException, DBReadWriteException{
        dataSource.createServiceDB(parameters);
    }

    public void getEverything() throws SQLException, DBReadWriteException {
        this.uslugi = dataSource.getAllServicesDB();
    }
    public void removeId(int a) throws SQLException {
        dataSource.removeServiceID(a);
    }
}
