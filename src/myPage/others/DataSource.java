package myPage.others;

import myPage.data.Client;
import myPage.data.User;
import myPage.exceptions.DBReadWriteException;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class DataSource {
    private Connection connection;
    private HashMap<String, PreparedStatement> statements;

    public DataSource(){
        connection = DataBaseManager.getConnection();
        statements = DataBaseManager.getStatements();
    }

    public User getUserDB(String e_mail) throws DBReadWriteException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        User user = null;

        exeStatement = statements.get("getClient");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next())
            user = new User(resultSet.getString("e_mail"), resultSet.getString("haslo"));
        else
            throw new DBReadWriteException();

        return user;
    }

    public void createClientDB(Client client) throws DBReadWriteException, SQLException{
        PreparedStatement exeStatement;
        int result;

        exeStatement = statements.get("createClient_P");
        exeStatement.setString(1, client.getE_mail());
        exeStatement.setString(2, client.getHaslo());
        exeStatement.setString(3, client.getImie());
        exeStatement.setString(4, client.getNazwisko());
        exeStatement.setString(5, client.getUlica());
        exeStatement.setInt(6, client.getKod_pocztowy());
        exeStatement.setString(7, client.getMiejscowosc());
        exeStatement.setDate(8, DateTransformer.getSqlDate(client.getData_urodzenia()));
        exeStatement.setInt(9, client.getTelefon());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClient_P");

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

        exeStatement = statements.get("assignClientCard_P");
        exeStatement.setString(1, client.getE_mail());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: assignClientCard_P");
    }

    public Client getClientDateDB(String e_mail) throws DBReadWriteException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        Client client;

        exeStatement = statements.get("getAccountData");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            client = new Client();
            client.setImie(resultSet.getString("imie"));
            client.setNazwisko(resultSet.getString("nazwisko"));
            client.setUlica(resultSet.getString("ulica"));
            client.setKod_pocztowy(resultSet.getInt("kod_pocztowy"));
            client.setMiejscowosc(resultSet.getString("miejscowosc"));
            client.setData_urodzenia(DateTransformer.getJavaDate(resultSet.getDate("data_urodzenia")));
            client.setTelefon(resultSet.getInt("telefon"));
            client.setE_mail(resultSet.getString("e_mail"));
            client.setIlosc_punktow(resultSet.getInt("ilosc_punktow"));
            client.setTyp_konta(resultSet.getString("typ_konta"));
        }else{
            throw new DBReadWriteException();
        }

        return client;
    }
}
