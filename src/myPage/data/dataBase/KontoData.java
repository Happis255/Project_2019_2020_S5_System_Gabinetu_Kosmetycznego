package myPage.data.dataBase;

import myPage.data.others.TypKonta;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KontoData extends Data{
    public KontoData(ResultSet resultQuery) {
        super(resultQuery);
    }

    public int getId() throws SQLException {
        return resultQuery.getInt("id_konta");
    }

    public String getHaslo() throws SQLException {
        return resultQuery.getString("haslo");
    }

    public TypKonta getTypKonta() throws SQLException {
        return TypKonta.getTypKonta(resultQuery.getString("typ_konta"));
    }
}
