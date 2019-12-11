package myPage.basicObjects;

import myPage.data.dataBase.AktualnoscData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Aktualnosci {

    private LinkedList<AktualnoscData> aktualnosci;
    private DataSource dataSource;

    public Aktualnosci() {
        dataSource = new DataSource();
        aktualnosci = new LinkedList<>();
    }

    public AktualnoscData AktualnoscPop(){
        return aktualnosci.pop();
    }

    public boolean aktualnoscEmpty(){
        return aktualnosci.isEmpty();
    }

    public int get_MaxId() throws SQLException, DBReadWriteException {
        return dataSource.getMaxIDAktualnosciDB();
    }

    public void register(HashMap<String, String> parameters) throws SQLException, ParseException, DBReadWriteException{
        dataSource.createAktualnoscDB(parameters);
    }

    public void getTodayUpdates() throws SQLException, DBReadWriteException {
        this.aktualnosci = dataSource.getAktualnosciDB();
    }
}
