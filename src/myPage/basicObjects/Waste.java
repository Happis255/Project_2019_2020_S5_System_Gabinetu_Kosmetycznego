package myPage.basicObjects;

import myPage.data.dataBase.WasteData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Waste {

    private LinkedList<WasteData> odpady;
    private DataSource dataSource;

    public Waste() {
        dataSource = new DataSource();
        odpady = new LinkedList<>();
    }

    public void getWaste() throws SQLException, DBReadWriteException {
        this.odpady = dataSource.getWaste();
    }

    public boolean wasteEmpty() {
        return odpady.isEmpty();
    }

    public WasteData wastePop() {
        return odpady.pop();
    }

    public void removeId(int a) throws SQLException {
        dataSource.removeWasteID(a);
    }

    public void register(HashMap<String, String> parameters)  throws SQLException, ParseException, DBReadWriteException {
        dataSource.createWasteDB(parameters);
    }

    public int count(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {
        return dataSource.countWaste(parameters);
    }
}
