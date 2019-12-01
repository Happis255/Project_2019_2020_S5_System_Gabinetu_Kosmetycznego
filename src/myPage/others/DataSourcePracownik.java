package myPage.others;

import myPage.data.Pracownik;
import myPage.exceptions.NoResultsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourcePracownik extends DataSource {

    public DataSourcePracownik(){
        super();
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
