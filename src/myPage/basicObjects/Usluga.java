package myPage.basicObjects;

import myPage.data.dataBase.UslugaData;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;

import java.sql.ResultSet;
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

    public void getEverything() throws SQLException{
        this.uslugi = dataSource.getAllServicesDB();
    }
    public void removeId(int a) throws SQLException {
        dataSource.removeServiceID(a);
    }

    public void getUslugiPracownikaID(int id) throws SQLException {
        this.uslugi = dataSource.getAllServicesForWorker(id);
    }

    public void removeAllowence(int id_uslugi, int id_prac) throws SQLException {
        dataSource.removeAllowenceDB(id_uslugi, id_prac);
    }

    public UslugaData getUsluga_ID(int id_uslugi) throws SQLException {
        return dataSource.getUsluga_ID_DB(id_uslugi);
    }

    public void getCzasUslugi(int id_uslugi) throws SQLException {
        ResultSet resultSet = dataSource.pobierzCzasUslugi(id_uslugi);
        while (resultSet.next()) {
            uslugi.push(new UslugaData(
                    resultSet.getInt("id_uslugi"),
                    resultSet.getString("typ_uslugi"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("opis"),
                    resultSet.getBoolean("czy_karta"),
                    resultSet.getInt("cena"),
                    resultSet.getInt("czas_trwania"),
                    resultSet.getString("wskazowki"),
                    resultSet.getInt("id_promocji")
            ));
        }
    }
}
