package myPage.basicObjects;

import myPage.data.dataBase.KontoData;
import myPage.data.dataBase.UserData;
import myPage.data.others.SessionData;
import myPage.data.others.TypKonta;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.CannotInstanciateException;
import myPage.exceptions.DBReadWriteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class User {

    protected int id;
    protected DataSource dataSource;

    public User(){
        dataSource = new DataSource();
    }

    public User(int idUser){
        this.id = idUser;
        dataSource = new DataSource();
    }

    public static User getInstance(int id, TypKonta typKonta) throws CannotInstanciateException {
        switch (typKonta){
            case KLIENT:
                return new Klient(id);
            case PRACOWNIK:
                return new Pracownik(id);
            case ADMINISTRATOR:
                return new Admin(id);
            default:
                throw new CannotInstanciateException();
        }
    }

    public boolean login(HttpServletRequest request, String username, String password) throws SQLException {
        ResultSet resultQuery = dataSource.getUserDB(username);
        HttpSession session = request.getSession();

        if(resultQuery.next()){
            if(resultQuery.getString("haslo").matches(password)){
                session.invalidate();
                session = request.getSession(true);
                SessionData sessionData = new SessionData(  resultQuery.getString("e_mail"),
                                                            TypKonta.getTypKonta(resultQuery.getString("typ_konta")),
                                                            resultQuery.getInt("id"));
                session.setAttribute("userData", sessionData);

            } else
                return false;
            return true;
        }else{
            return false;
        }
    }

    public void logout(HttpSession session){
        session.invalidate();
    }

    public void register(HashMap<String, String> parameters) throws SQLException, ParseException, DBReadWriteException {
        dataSource.CheckUserDB(parameters.get("e-mail"));
        dataSource.createClientDB(parameters);
    }

    public UserData getData() throws SQLException {
        return null;
    }

    public KontoData getAccount() throws SQLException, DBReadWriteException {
        return null;
    }
}
