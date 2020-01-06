package myPage.basicObjects;
import myPage.data.dataBase.RaportData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;

import java.sql.SQLException;
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
}
