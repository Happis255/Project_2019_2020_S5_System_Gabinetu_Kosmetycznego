package myPage.data.dataBase;

import myPage.others.DateTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserData extends Data{
    public UserData(ResultSet resultQuery){
        super(resultQuery);
    }

    public int getId() throws SQLException {
        return resultQuery.getInt("id");
    }

    public String getImie() throws SQLException {
        return resultQuery.getString("imie");
    }

    public String getNazwisko() throws SQLException {
        return resultQuery.getString("nazwisko");
    }

    public String getUlica() throws SQLException {
        return resultQuery.getString("ulica");
    }

    public String getKod_pocztowy() throws SQLException {
        return resultQuery.getString("kod_pocztowy");
    }

    public String getMiejscowosc() throws SQLException {
        return resultQuery.getString("miejscowosc");
    }

    public Date getData_urodzenia() throws SQLException {
        return DateTransformer.getJavaDate(resultQuery.getDate("data_urodzenia"));
    }

    public int getTelefon() throws SQLException {
        return resultQuery.getInt("telefon");
    }

    public String getE_mail() throws SQLException {
        return resultQuery.getString("e_mail");
    }

    public int getId_konta() throws SQLException {
        return resultQuery.getInt("id_konta");
    }
}
