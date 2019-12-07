package myPage.basicObjects;

import myPage.data.dataBase.KlientData;
import myPage.data.dataBase.KontoData;
import myPage.data.others.TypKonta;
import myPage.exceptions.DBReadWriteException;
import myPage.others.DateTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Klient extends User{

    public Klient(int idKlienta){
        super(idKlienta);
    }

    @Override
    public KlientData getData() throws SQLException {
        ResultSet resultQuery = dataSource.getClientDB(id);
        KlientData klientData = null;

        if(resultQuery.next()){
            klientData = new KlientData(
                    resultQuery.getInt("id_klienta"),
                    resultQuery.getString("imie"),
                    resultQuery.getString("nazwisko"),
                    resultQuery.getString("ulica"),
                    resultQuery.getString("kod_pocztowy"),
                    resultQuery.getString("miejscowosc"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data_urodzenia")),
                    resultQuery.getInt("telefon"),
                    resultQuery.getString("e_mail"),
                    resultQuery.getInt("id_konta"),

                    resultQuery.getInt("ilosc_punktow"),
                    resultQuery.getInt("id_karty"),
                    resultQuery.getInt("id_statusu")
            );
        }

        return klientData;
    }

    @Override
    public KontoData getAccount() throws SQLException, DBReadWriteException {
        ResultSet resultQuery = dataSource.getClientAccountDB(id);
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
}
