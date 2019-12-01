package myPage.others;

import myPage.data.*;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.NoResultsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DataSource {
    private Connection connection;
    private HashMap<String, PreparedStatement> statements;

    public DataSource(){
        connection = DataBaseManager.getConnection();
        statements = DataBaseManager.getStatements();
    }

    /* Metody wykorzystywane do komunikacji z bazą danych */
    public void createClientDB(Client client) throws DBReadWriteException, SQLException{
        PreparedStatement exeStatement;
        int result;

        exeStatement = statements.get("createClientCard_P");
        exeStatement.setBoolean(1, client.isP_p1());
        exeStatement.setBoolean(2, client.isP_p2());
        exeStatement.setBoolean(3, client.isP_p3());
        exeStatement.setBoolean(4, client.isP_p4());
        exeStatement.setBoolean(5, client.isP_p5());
        exeStatement.setBoolean(6, client.isP_p6());
        exeStatement.setBoolean(7, client.isP_p7());
        exeStatement.setBoolean(8, client.isP_p8());
        exeStatement.setBoolean(9, client.isP_p9());
        exeStatement.setString(10, client.getP_ocena_skory());
        exeStatement.setString(11, client.getP_rodzaj_jakosc());
        exeStatement.setString(12, client.getP_wrazliwosc());
        exeStatement.setString(13, client.getP_inne_uwagi());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClientCard_P");

        exeStatement = statements.get("createClient_P");
        exeStatement.setString(1, client.getE_mail());
        exeStatement.setString(2, client.getHaslo());
        exeStatement.setString(3, client.getImie());
        exeStatement.setString(4, client.getNazwisko());
        exeStatement.setString(5, client.getUlica());
        exeStatement.setString(6, client.getKod_pocztowy());
        exeStatement.setString(7, client.getMiejscowosc());
        exeStatement.setDate(8, DateTransformer.getSqlDate(client.getData_urodzenia()));
        exeStatement.setInt(9, client.getTelefon());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClient_P");

        exeStatement = statements.get("assignClientCard_P");
        exeStatement.setString(1, client.getE_mail());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: assignClientCard_P");
    }

    public Client getClientDB(String e_mail) throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        Client client;

        exeStatement = statements.get("getAccountData");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            client = new Client();
            getUser(client, resultSet);
            client.setIlosc_punktow(resultSet.getInt("ilosc_punktow"));
        }else{
            throw new NoResultsException();
        }

        return client;
    }

    public Aktualnosci getAktualnosci() throws NoResultsException, SQLException{

        PreparedStatement exeStatement;
        ResultSet resultSet;
        Aktualnosci aktualnosc = new Aktualnosci();
        exeStatement = statements.get("pobierz_dzisiaj_aktualnosci_P");

        /* Pobieramy wynik zapytania */
        resultSet = exeStatement.executeQuery();

        /* Uzupełniamy klasę wynikami
        *  Każdy wiersz z treścią i każdy wiersz z tytulem trzeba umiescic w stosie w klasie Aktualnosc */
        resultSet.last();
        if(resultSet.getRow() == 0)
            throw new NoResultsException();
        resultSet.beforeFirst();

        while (resultSet.next()){
            aktualnosc.setID(resultSet.getInt("id_aktualnosci"));
            aktualnosc.setTytul(resultSet.getString("tytul"));
            aktualnosc.setTresc(resultSet.getString("tresc"));
        }
        return aktualnosc;
    }

    public User[] getAllAccountsBasicDataWithTagDB(TypKonta accountType)throws SQLException{
        User[] users;
        PreparedStatement exeStatement;
        ResultSet resultSet;
        String str = TypKonta.getStringVal(accountType);

        exeStatement = statements.get("getAllAccountsBasicDataWithTag");
        exeStatement.setString(1, str);
        resultSet = exeStatement.executeQuery();

        resultSet.last();
        int numOfRows = resultSet.getRow();
        users = new User[numOfRows];
        resultSet.beforeFirst();

        int i=0;
        while (resultSet.next()){
            users[i] = new User();
            users[i].setE_mail(resultSet.getString("e_mail"));
            users[i].setImie(resultSet.getString("imie"));
            users[i].setNazwisko(resultSet.getString("nazwisko"));
            users[i].setTyp_konta(resultSet.getString("typ_konta"));
            ++i;
        }

        return users;
    }

    public User getUserDB(String e_mail) throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        User user;

        exeStatement = statements.get("getUser");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            user = new User();
            user.setE_mail(resultSet.getString("e_mail"));
            user.setTyp_konta(resultSet.getString("typ_konta"));
            user.setHaslo(resultSet.getString("haslo"));
        }else{
            throw new NoResultsException();
        }

        return user;
    }

    public Pracownik getPracownikDB(String e_mail) throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        Pracownik worker;

        exeStatement = statements.get("getWorker");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            worker = new Pracownik();
            getUser(worker, resultSet);
            worker.setPesel(resultSet.getInt("pesel"));
            worker.setData_zatrudnienia(DateTransformer.getJavaDate(resultSet.getDate("data_zatrudnienia")));
            worker.setCertyfikaty(resultSet.getString("certyfikaty"));

        }else{
            throw new NoResultsException();
        }

        return worker;
    }

    private void getUser(User user, ResultSet resultSet) throws SQLException {
        user.setImie(resultSet.getString("imie"));
        user.setNazwisko(resultSet.getString("nazwisko"));
        user.setUlica(resultSet.getString("ulica"));
        user.setKod_pocztowy(resultSet.getString("kod_pocztowy"));
        user.setMiejscowosc(resultSet.getString("miejscowosc"));
        user.setData_urodzenia(DateTransformer.getJavaDate(resultSet.getDate("data_urodzenia")));
        user.setTelefon(resultSet.getInt("telefon"));
        user.setE_mail(resultSet.getString("e_mail"));
        user.setTyp_konta(resultSet.getString("typ_konta"));
    }
}
