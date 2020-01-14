package myPage.UnitTests;

import myPage.basicObjects.Wizyta;
import myPage.dataSourceDB.DataBaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WizytaTest {
    private PreparedStatement statement;
    private Wizyta wizyta;

    @BeforeEach
    void setUp() {
        wizyta = new Wizyta();
    }

    @AfterEach
    void tearDown() {
        wizyta = null;
    }

    @Test
    void isEmpty() {
        assertEquals(wizyta.isEmpty(), true);
    }

    @Test
    void getTodayWizytyNumTest() {
        try {
            wizyta.getTodayWizyty();

            PreparedStatement exeStatement;
            exeStatement = DataBaseManager.getStatements().get("pobierz_today_wzity_P");

            ResultSet resultSet = exeStatement.executeQuery();

            resultSet.last();
            int numTest = resultSet.getRow();
            assertEquals(numTest, wizyta.size());

        } catch (SQLException e) {
            fail("SQL ExceptionThrown!");
        } catch (NullPointerException e){
            fail("cannot run getTodayWizyty()");
        }
    }
}