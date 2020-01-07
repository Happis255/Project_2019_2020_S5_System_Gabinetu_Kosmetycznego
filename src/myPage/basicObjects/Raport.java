package myPage.basicObjects;
import myPage.data.dataBase.RaportData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Raport {

    private LinkedList<RaportData> raporty;
    private DataSource dataSource;

    public Raport() {
        dataSource = new DataSource();
        raporty = new LinkedList<>();
    }


    public void getPracownikRaporty(int id) throws SQLException, DBReadWriteException {
        this.raporty = dataSource.getRaportsWorker(id);
    }

    public boolean raportyEmpty() {
        return raporty.isEmpty();
    }

    public RaportData raportyPop() {
        return raporty.pop();
    }

    public void removeId(int id) throws SQLException {
        dataSource.removeRaportID(id);
    }

    public void register(HashMap<String, String> parameters) throws SQLException, ParseException, DBReadWriteException {
        dataSource.createRaportDB(parameters);
    }
}
