package myPage.data.dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KsiazeczkaZdrowiaData extends Data{
    public KsiazeczkaZdrowiaData(ResultSet resultQuery) {
        super(resultQuery);
    }

    public int getId_ksiazeczki() throws SQLException {
        return resultQuery.getInt("id_ksiazeczki");
    }

    public boolean getRozrusznik_serca() throws SQLException {
        return resultQuery.getBoolean("rozrusznik_serca");
    }

    public boolean getHermofilia() throws SQLException {
        return resultQuery.getBoolean("hermofilia");
    }

    public boolean getLuszczyca() throws SQLException {
        return resultQuery.getBoolean("luszczyca");
    }

    public String getAlergia() throws SQLException {
        return resultQuery.getString("alergia");
    }

    public String getPrzebarwienie() throws SQLException {
        return resultQuery.getString("przebarwienie");
    }

    public String getChoroba_zakazna() throws SQLException {
        return resultQuery.getString("choroba_zakazna");
    }

    public String getZaburzenia_ukrwienia() throws SQLException {
        return resultQuery.getString("zaburzenia_ukrwienia");
    }

    public String getOpryszczka() throws SQLException {
        return resultQuery.getString("goraczka");
    }

    public String getOslabienie() throws SQLException {
        return resultQuery.getString("oslabienie");
    }

    public boolean getCiaza() throws SQLException {
        return resultQuery.getBoolean("ciaza");
    }
}
