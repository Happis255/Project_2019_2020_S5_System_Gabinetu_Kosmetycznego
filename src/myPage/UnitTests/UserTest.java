package myPage.UnitTests;

import myPage.basicObjects.User;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class UserTest {
    @Test
    void checkAddAlreadyExistingUser() {
        String userName = "ppiskorz0@gmail.com";

        Assertions.assertThrows(DBReadWriteException.class, () -> {
            User user = new User();
            ResultSet resultSet = null;

            DataSource dataSource = new DataSource();
            try {
                resultSet = dataSource.getUserDB(userName);

                assertNotNull(resultSet);

                resultSet.next();

                assertNotNull(resultSet);

                HashMap<String, String> parameters = new HashMap<>();

                //parameters.put("e-mail", resultSet.getString("e-mail"));
                parameters.put("e-mail", userName);
                parameters.put("haslo", "-");
                parameters.put("imie", "-");
                parameters.put("nazwisko", "-");
                parameters.put("data-urodzenia", "-");
                parameters.put("telefon", "-");
                parameters.put("ulica", "-");
                parameters.put("kod", "-");
                parameters.put("miejscowosc", "-");

                user.register(parameters);

            } catch (SQLException e) {
                fail("SQLException Thrown!");
            } catch (ParseException e) {
                fail("ParseException Thrown!");
            }

            fail("u cant add already existing user");
        });



    }
}