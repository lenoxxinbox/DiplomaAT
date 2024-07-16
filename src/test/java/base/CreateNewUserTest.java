package base;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateNewUserTest extends BaseTest {

    @BeforeEach
    public void login() {
        loginPage.fullAuthorization();
        MainPage.openCreateNewUserPage();

    }


    @Test
    @DisplayName("Создание пользователя с пустыми полями")
    @Owner("Julia Sinkova")
    public void createUserWithEmptyFields() {
        String targetStatusMessage = "Status: not pushed";
        String statusMessage = createNewUserPage.isPageOpen().clickPushButton().getStatusMessage("Status: not pushed");
        Assertions.assertEquals(statusMessage, targetStatusMessage, "status message should be correct");
    }

    @Test
    @DisplayName("Создание пользователя")
    @Owner("Julia Sinkova")
    public void createUser() throws SQLException {
        String targetStatusMessage = "Status: Successfully pushed, code: 201";
        String firstName = "User";
        String secondName = "Test";
        int age = 100;
        int money = 10000;
        boolean isMale = false;
        String statusMessage = createNewUserPage.isPageOpen().createUser(firstName, secondName, age, money, isMale)
                .getStatusMessage(targetStatusMessage);
        String id = createNewUserPage.getNewUserId();
        ResultSet dbResult = dbConnection.getUserById(id);

        String firstNameFromDB = null;
        String secondNameFromDB = null;
        int ageFromDB = 0;
        int moneyFromDB = 0;
        boolean isMaleFromDB = false;

        while (dbResult.next()) {
            firstNameFromDB = dbResult.getString("first_name");
            secondNameFromDB = dbResult.getString("second_name");
            ageFromDB = dbResult.getInt("age");
            moneyFromDB = dbResult.getInt("money");
            isMaleFromDB = dbResult.getBoolean("sex");
        }
        // effectively final copies of variables for lambda functions to accept
        String finalFirstNameFromDB = firstNameFromDB;
        String finalSecondNameFromDB = secondNameFromDB;
        int finalAgeFromDB = ageFromDB;
        int finalMoneyFromDB = moneyFromDB;
        boolean finalIsMaleFromDB = isMaleFromDB;
        Assertions.assertAll(
                () -> Assertions.assertEquals(targetStatusMessage, statusMessage, "status message should be correct"),
                () -> Assertions.assertEquals(firstName, finalFirstNameFromDB, "first name from data base should match the one used to create user"),
                () -> Assertions.assertEquals(secondName, finalSecondNameFromDB, "second name from data base should match the one used to create user"),
                () -> Assertions.assertEquals(age, finalAgeFromDB, "age from data base should match the one used to create user"),
                () -> Assertions.assertEquals(money, finalMoneyFromDB, "money from data base should match the one used to create user"),
                () -> Assertions.assertEquals(isMale, finalIsMaleFromDB, "sex from data base should match the one used to create user")

        );


    }
}
