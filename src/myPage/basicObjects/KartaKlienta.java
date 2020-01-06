package myPage.basicObjects;
import myPage.data.dataBase.KartaKlientaData;
import myPage.dataSourceDB.DataSource;

import java.sql.SQLException;

public class KartaKlienta {

    protected DataSource dataSource;

    public KartaKlienta(){
        dataSource = new DataSource();
    }

    public KartaKlientaData KartaKlientaData_get(int id) throws SQLException {
        return dataSource.getKartaKlientaID(id);
    }
}
