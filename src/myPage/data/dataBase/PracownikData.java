package myPage.data.dataBase;

import myPage.others.DateTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PracownikData extends UserData{
    public PracownikData(ResultSet resultQuery) {
        super(resultQuery);
    }

    @Override
    public int getId() throws SQLException {
        return resultQuery.getInt("id_pracownika");
    }

    public int getPesel() throws SQLException {
        return resultQuery.getInt("pesel");
    }

    public Date getData_zatrudnienia() throws SQLException {
        return DateTransformer.getJavaDate(resultQuery.getDate("data_zatrudnienia"));
    }

    public String getCertyfikaty() throws SQLException {
        return resultQuery.getString("certyfikaty");
    }

    public int getId_ksiazeczki() throws SQLException {
        return resultQuery.getInt("id_ksiazeczki");
    }
}
