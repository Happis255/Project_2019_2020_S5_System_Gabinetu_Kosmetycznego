package myPage.basicObjects;

import myPage.data.dataBase.KsiazeczkaZdrowiaData;
import myPage.dataSourceDB.DataSource;

import java.sql.SQLException;

public class KsiazeczkaZdrowia {

    protected DataSource dataSource;

    public KsiazeczkaZdrowia(){
        dataSource = new DataSource();
    }

    /* Zwraca obiekt ksiazeczki zdrowia odczytanej z bazy danych */
    public KsiazeczkaZdrowiaData getKsiazeczkaID(int id) throws SQLException {
        KsiazeczkaZdrowiaData wynik = dataSource.getKsiazeczkaZdrowiaID(id);
        return wynik;
    }
}
