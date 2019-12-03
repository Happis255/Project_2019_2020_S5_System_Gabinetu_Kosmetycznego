package myPage.data.dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlientData extends UserData{
    public KlientData(ResultSet resultQuery) {
        super(resultQuery);
    }

    @Override
    public int getId() throws SQLException {
        return resultQuery.getInt("id_klienta");
    }

    public int getIloscPunktow() throws SQLException {
        return resultQuery.getInt("ilosc_punktow");
    }

    public int getId_karty() throws SQLException {
        return resultQuery.getInt("id_karty");
    }

    public int getId_statusu() throws SQLException {
        return resultQuery.getInt("id_statusu");
    }
}
