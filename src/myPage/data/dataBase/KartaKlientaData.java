package myPage.data.dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KartaKlientaData extends Data {
    public KartaKlientaData(ResultSet resultQuery) {
        super(resultQuery);
    }

    public int getId_karty() throws SQLException {
        return resultQuery.getInt("id_karty");
    }

    public boolean getP1() throws SQLException {
        return resultQuery.getBoolean("p1");
    }

    public boolean getP2() throws SQLException {
        return resultQuery.getBoolean("p2");
    }

    public boolean getP3() throws SQLException {
        return resultQuery.getBoolean("p3");
    }

    public boolean getP4() throws SQLException {
        return resultQuery.getBoolean("p4");
    }

    public boolean getP5() throws SQLException {
        return resultQuery.getBoolean("p5");
    }

    public boolean getP6() throws SQLException {
        return resultQuery.getBoolean("p6");
    }

    public boolean getP7() throws SQLException {
        return resultQuery.getBoolean("p7");
    }

    public boolean getP8() throws SQLException {
        return resultQuery.getBoolean("p8");
    }

    public boolean getP9() throws SQLException {
        return resultQuery.getBoolean("p9");
    }

    public String getOcenaSkory() throws SQLException {
        return resultQuery.getString("ocena_skory");
    }

    public String getRodzaj_jakosc() throws SQLException {
        return resultQuery.getString("rodzaj_jakos");
    }

    public String getWrazliwosc() throws SQLException {
        return resultQuery.getString("wrazliwosc");
    }

    public String getInne_uwagi() throws SQLException {
        return resultQuery.getString("inne_uwagi");
    }
}
