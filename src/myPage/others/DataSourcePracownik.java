package myPage.others;

import myPage.data.Aktualnosci;
import myPage.data.Pracownik;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.NoResultsException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourcePracownik extends DataSource {

    public DataSourcePracownik(){
        super();
    }

    public void createDBAktualnosc(Aktualnosci news) throws DBReadWriteException, SQLException {
        PreparedStatement exeStatement;
        int result;

        exeStatement = statements.get("createNews_P");
        exeStatement.setString(1, news.getTytul());
        exeStatement.setString(2, news.getTresc());
        exeStatement.setDate(3,  DateTransformer.getSqlDate(news.getData_od()));
        exeStatement.setDate(4, DateTransformer.getSqlDate(news.getData_do()));
        exeStatement.setInt(4, news.getID_Pracownika());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createNews_P");
    }

    public Pracownik getPracownikDB(String e_mail) throws NoResultsException, SQLException {
        PreparedStatement exeStatement;
        ResultSet resultSet;
        Pracownik worker;

        exeStatement = statements.get("getWorker");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            worker = new Pracownik();
            getUser(worker, resultSet);
            worker.setPesel(resultSet.getInt("pesel"));
            worker.setData_zatrudnienia(DateTransformer.getJavaDate(resultSet.getDate("data_zatrudnienia")));
            worker.setCertyfikaty(resultSet.getString("certyfikaty"));

        }else{
            throw new NoResultsException();
        }

        return worker;
    }
}
