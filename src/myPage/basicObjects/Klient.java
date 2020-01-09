package myPage.basicObjects;

import myPage.data.dataBase.AktualnoscData;
import myPage.data.dataBase.KlientData;
import myPage.data.dataBase.KontoData;
import myPage.data.others.TypKonta;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.NoResultsException;
import myPage.others.DateTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Klient extends User{

    public Klient(int idKlienta){
        super(idKlienta);
    }
    private LinkedList<KlientData> klienci = new LinkedList<>();

    public Klient() {

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

    public String getAccountStatusName(int id_statusu) throws SQLException, NoResultsException {
        String nazwa = dataSource.getClientStatusNameDB(id_statusu);
        return nazwa;
    }

    public void removeClient(int id_klienta) throws SQLException {
        dataSource.removeClientDN(id_klienta);
    }

    public void editClient(HashMap<String, String> parameters) throws SQLException, DBReadWriteException, ParseException {
        dataSource.CheckUserDB(parameters.get("e_mail"));
        dataSource.UpdateClientDB(parameters);
    }

    public void editClientBook(HashMap<String, String> parameters) throws SQLException {
        dataSource.editClientBookDB(parameters);
    }

    public KlientData KlientPop(){
        return klienci.pop();
    }

    public boolean KlientEmpty(){
        return klienci.isEmpty();
    }

    public void getAllData() throws SQLException {
        this.klienci = dataSource.getAllClientsDB();
    }
}
