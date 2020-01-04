package myPage.basicObjects;

import myPage.data.dataBase.NieobecnoscData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Nieobecnosc {

    private LinkedList<NieobecnoscData> uslugi;
    private DataSource dataSource;

    public Nieobecnosc() {
        dataSource = new DataSource();
        uslugi = new LinkedList<>();
    }

    public NieobecnoscData nieobecnoscPop(){
        return uslugi.pop();
    }

    public boolean nieobecnoscEmpty(){
        return uslugi.isEmpty();
    }

    public void register(HashMap<String, String> parameters) throws SQLException, ParseException, DBReadWriteException{
        dataSource.createNieobecnoscDB(parameters);
    }

    public void getPracownikNieobecnosc(int id) throws SQLException, DBReadWriteException {
        this.uslugi = dataSource.getAbsenceWorker(id);
    }

    public void getNieobecnosci() throws SQLException, DBReadWriteException {
        this.uslugi = dataSource.getAbsencesAll();
    }

    public void removeId(int a) throws SQLException {
        dataSource.removeAbsenceID(a);
    }

    public void changeToNo(int a) throws SQLException {
        dataSource.declineAbsenceID(a);
    }

    public void changeToYes(int a) throws SQLException {
        dataSource.approveAbsenceID(a);
    }

    public void setStatus(int id, int status) throws SQLException {
        //dataSource.setStatusNieobecnosci(id, status);
    }
}
