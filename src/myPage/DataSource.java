package myPage;

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

    public User getUserDB(String e_mail){
        PreparedStatement exeStatement;
        ResultSet resultSet;
        User user = null;
        try {
            System.out.println("statements: " + statements);
            exeStatement = statements.get("getClient");
            System.out.println("exe: " + exeStatement);
            exeStatement.setString(1, e_mail);
            resultSet = exeStatement.executeQuery();

            if (resultSet.next()){
                user = new User(resultSet.getString("e_mail"), resultSet.getString("haslo"));
            }

        } catch (SQLException e) {
            System.out.println("ERR:" + e.getClass().getName() + ": " + e.getMessage());
        }
        return user;
    }

    public void createClientDB(Client client, Account account){
        PreparedStatement exeStatement;
        int result;

        try{
            exeStatement = statements.get("procedureAddAccount");
            exeStatement.setString(1, client.getE_mail());
            exeStatement.setString(2, account.getHaslo());
            exeStatement.setString(3, client.getImie());
            exeStatement.setString(4, client.getNazwisko());
            exeStatement.setString(5, client.getUlica());
            exeStatement.setInt(6, client.getKod_pocztowy());
            exeStatement.setString(7, client.getMiejscowosc());
            java.sql.Date sqlDate = new java.sql.Date(client.getData_urodzenia().getTime());
            exeStatement.setDate(8, sqlDate);
            exeStatement.setInt(9, client.getTelefon());
            exeStatement.setBoolean(10, client.getP_p1());
            exeStatement.setBoolean(11, client.getP_p2());
            exeStatement.setBoolean(12, client.getP_p3());
            exeStatement.setBoolean(13, client.getP_p4());
            exeStatement.setBoolean(14, client.getP_p5());
            exeStatement.setBoolean(15, client.getP_p6());
            exeStatement.setBoolean(16, client.getP_p7());
            exeStatement.setBoolean(17, client.getP_p8());
            exeStatement.setBoolean(18, client.getP_p9());
            exeStatement.setString(19, client.getP_ocena_skory());
            exeStatement.setString(20, client.getP_rodzaj_jakosc());
            exeStatement.setString(21, client.getP_wrazliwosc());
            exeStatement.setString(22, client.getP_inne_uwagi());
            result = exeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
