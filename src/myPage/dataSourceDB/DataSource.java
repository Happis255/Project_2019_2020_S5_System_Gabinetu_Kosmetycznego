package myPage.dataSourceDB;

import myPage.data.dataBase.AktualnoscData;
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
import java.util.LinkedList;

public class DataSource {
    protected Connection connection;
    protected HashMap<String, PreparedStatement> statements;

    public DataSource(){
        connection = DataBaseManager.getConnection();
        statements = DataBaseManager.getStatements();
    }

    /* Metody wykorzystywane do komunikacji z bazą danych */
    public LinkedList<AktualnoscData> getAktualnosciDB() throws SQLException{

        LinkedList<AktualnoscData> news_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_dzisiaj_aktualnosci_P");
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){
            news_list.push(new AktualnoscData(
                    resultSet.getInt("id_aktualnosci"),
                    resultSet.getString("tytul"),
                    resultSet.getString("tresc"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getInt("id_pracownika")));
            System.out.println("Dodalem :)");
        }
        return news_list;
    }

    public int getMaxIDAktualnosciDB() throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_max_id_aktualnosci_p");
        resultSet = exeStatement.executeQuery();
        if (resultSet.next()){
            int wynik = resultSet.getInt("id_aktualnosci");
            return wynik;
        } else
            throw new NoResultsException();
    }

    /* Metoda odpowiedzialna za dodawanie news'a do bazy danych */
    public void createAktualnoscDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement;
        int result;
        exeStatement = statements.get("createNews_P");
        exeStatement.setString(1, parameters.get("tytul"));
        exeStatement.setString(2, parameters.get("opis"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_od = dateFormat.parse(parameters.get("data-od"));
        Date date_do = dateFormat.parse(parameters.get("data-do"));
        exeStatement.setDate(3, DateTransformer.getSqlDate(date_od));
        exeStatement.setDate(4, DateTransformer.getSqlDate(date_do));
        exeStatement.setInt(5, Integer.parseInt(parameters.get("id-pracownika")));
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createNews_P");
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
