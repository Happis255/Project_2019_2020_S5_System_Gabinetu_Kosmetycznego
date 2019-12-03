package myPage.dataSourceDB;

import myPage.data.dataBase.Aktualnosci;
import myPage.data.others.TypKonta;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.NoResultsException;
import myPage.others.DateTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DataSource {
    protected Connection connection;
    protected HashMap<String, PreparedStatement> statements;

    public DataSource(){
        connection = DataBaseManager.getConnection();
        statements = DataBaseManager.getStatements();
    }

    /* Metody wykorzystywane do komunikacji z bazą danych */
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

    public ResultSet getUserDB(String e_mail) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getUser");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public void createClientDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {
        PreparedStatement exeStatement;
        int result;

        exeStatement = statements.get("createClientCard_P");
        exeStatement.setBoolean(1, false);
        exeStatement.setBoolean(2, false);
        exeStatement.setBoolean(3, false);
        exeStatement.setBoolean(4, false);
        exeStatement.setBoolean(5, false);
        exeStatement.setBoolean(6, false);
        exeStatement.setBoolean(7, false);
        exeStatement.setBoolean(8, false);
        exeStatement.setBoolean(9, false);
        exeStatement.setString(10, "-");
        exeStatement.setString(11, "-");
        exeStatement.setString(12, "-");
        exeStatement.setString(13, "-");
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClientCard_P");

        exeStatement = statements.get("createClient_P");
        exeStatement.setString(1, parameters.get("e-mail"));
        exeStatement.setString(2, parameters.get("haslo"));
        exeStatement.setString(3, parameters.get("imie"));
        exeStatement.setString(4, parameters.get("nazwisko"));
        exeStatement.setString(5, parameters.get("ulica"));
        exeStatement.setString(6, parameters.get("kod"));
        exeStatement.setString(7, parameters.get("miejscowosc"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(parameters.get("data-urodzenia"));

        exeStatement.setDate(8, DateTransformer.getSqlDate(date));
        exeStatement.setInt(9, Integer.parseInt(parameters.get("telefon")));
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClient_P");

        exeStatement = statements.get("assignClientCard_P");
        exeStatement.setString(1, parameters.get("e-mail"));
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: assignClientCard_P");
    }

    public ResultSet getClientDB(int idKlienta) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getClient");
        exeStatement.setInt(1, idKlienta);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getClientAccountDB(int idKlienta) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getClientAccountData");
        exeStatement.setInt(1, idKlienta);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getPracownikDB(int idPracownika) throws SQLException {
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getPracownik");
        exeStatement.setInt(1, idPracownika);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getPracownikAccountDB(int idKlienta) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getClientAccountData");
        exeStatement.setInt(1, idKlienta);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getAllAccountsBasicDataWithTagDB(TypKonta accountType)throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        String typKonta = TypKonta.getStringVal(accountType);

        exeStatement = statements.get("getAllAccountsBasicDataWithTag");
        exeStatement.setString(1, typKonta);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }
}
