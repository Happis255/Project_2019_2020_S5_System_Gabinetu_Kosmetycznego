package myPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class DataBaseManager {
    private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/id11689767_projektgabinetgracja";
    private final static String USER_NAME = "root";
    private final static String USER_PASSWD = "";
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";

    private static Connection connection;
    private static HashMap<String, PreparedStatement> statements;

    static{
        try {
            Class.forName(DB_DRIVER);
            statements = new HashMap<>();
            connection = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWD);
            statements.put("getClient", connection.prepareStatement("select * from konto join klient on konto.id_konta=klient.id_konta where klient.e_mail=?"));
            statements.put("addClient", connection.prepareStatement("insert into klient (id_klienta, imie, nazwisko, ulica, kod_pocztowy, miejscowosc, data_urodzenia, " +
                    "telefon, e_mail, ilosc_punktow, id_karty, id_statusu, id_konta) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
            statements.put("getAccount", connection.prepareStatement("select * from konto where "));
            statements.put("addAccount", connection.prepareStatement("insert into konto (id_konta, haslo, typ_konta) values (?, ?, ?)"));
            statements.put("procedureAddAccount", connection.prepareStatement("{call utworz_klienta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, PreparedStatement> getStatements(){
        return statements;
    }

    public static Connection getConnection(){
        return connection;
    }
}
