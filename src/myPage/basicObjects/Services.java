package myPage.basicObjects;

import myPage.data.dataBase.ServiceData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Services {

    private LinkedList<ServiceData> przeglady;
    private DataSource dataSource;

    public Services() {
        dataSource = new DataSource();
        przeglady = new LinkedList<ServiceData>();
    }

    public void getPracownikPrzeglady(int id) throws SQLException, DBReadWriteException {
        this.przeglady = dataSource.getServiceWorker(id);
    }

    public boolean serviceEmpty() {
        return przeglady.isEmpty();
    }

    public ServiceData servicePop() {
        return przeglady.pop();
    }

    public void removeId(int id) throws SQLException {
        dataSource.removeServicesID(id);
    }

    public void register(HashMap<String, String> parameters) throws SQLException, ParseException, DBReadWriteException  {
            dataSource.createPrzegladDB(parameters);
    }

    public void getPrzeglady() throws SQLException {
        this.przeglady = dataSource.getRaportSprzet();
    }
}
