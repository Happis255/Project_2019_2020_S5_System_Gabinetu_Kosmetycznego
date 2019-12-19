package myPage.basicObjects;

import myPage.data.dataBase.KontoData;
import myPage.data.dataBase.PracownikData;
import myPage.data.others.TypKonta;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;
import myPage.others.DateTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class Pracownik extends User{

    public Pracownik(int idPracownika){
        super(idPracownika);
    }

    @Override
    public PracownikData getData() throws SQLException {
        ResultSet resultQuery = dataSource.getPracownikDB(id);
        PracownikData pracownikData = null;

        if(resultQuery.next()){
            pracownikData = new PracownikData(
                    resultQuery.getInt("id_pracownika"),
                    resultQuery.getString("imie"),
                    resultQuery.getString("nazwisko"),
                    resultQuery.getString("ulica"),
                    resultQuery.getString("kod_pocztowy"),
                    resultQuery.getString("miejscowosc"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data_urodzenia")),
                    resultQuery.getInt("telefon"),
                    resultQuery.getString("e_mail"),
                    resultQuery.getInt("id_konta"),

                    resultQuery.getInt("pesel"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data_zatrudnienia")),
                    resultQuery.getString("certyfikaty"),
                    resultQuery.getInt("id_ksiazeczki")
            );
        }

        return pracownikData;
    }

    @Override
    public KontoData getAccount() throws SQLException, DBReadWriteException {
        ResultSet resultQuery = dataSource.getPracownikAccountDB(id);
        KontoData kontoData = null;

        //sprawdzanie ilosci wierszy wyniku (musi byc 1 inaczej error)
        resultQuery.last();
        if(resultQuery.getRow() != 1)
            throw new DBReadWriteException();
        resultQuery.beforeFirst();

        //pobieranie danych
        if(resultQuery.next()){
            kontoData = new KontoData(
                    resultQuery.getInt("id_konta"),
                    resultQuery.getString("haslo"),
                    TypKonta.getTypKonta(resultQuery.getString("typ_konta"))
            );
        }

        return kontoData;
    }

    public void addEvent(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {
        DataSource dataSource = new DataSource();
        dataSource.createEventDB(parameters);
    }
}
