package base;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateNewUserTest extends BaseTest {

    @BeforeEach
    public void login() {
        loginPage.isPageOpen().fullAuthorization();
        menu.openCreateNewUserPage();

    }

    @Test
    @DisplayName("Создание пользователя с пустыми полями")
    @Owner("Julia Sinkova")
    public void createUserWithEmptyFields() {
        String targetStatusMessage = "Status: Invalid request data";
        boolean isStatusCorrect = createNewUserPage.isPageOpen().clickPushButton().isStatusMessageCorrect(targetStatusMessage);
        Assertions.assertTrue(isStatusCorrect, "status message should be correct");
    }

    @Test
    @DisplayName("Создание пользователя без заполнения поля first_name")
    @Owner("Julia Sinkova")
    public void createUserWithoutFirstName() {
        String targetStatusMessage = "Status: Invalid request data";
        String secondName = "example";
        int age = 1;
        int money = 1000;
        boolean isMale = false;
        boolean isStatusCorrect = createNewUserPage.isPageOpen().createUser("", secondName, age, money, isMale).isStatusMessageCorrect(targetStatusMessage);
        Assertions.assertTrue(isStatusCorrect, "status message should be correct");
    }

    @Test
    @DisplayName("Создание пользователя без заполнения поля second_name")
    @Owner("Julia Sinkova")
    public void createUserWithoutSecondName() {
        String targetStatusMessage = "Status: Invalid request data";
        String firstName = "TestTest";
        int age = 1000;
        int money = 1;
        boolean isMale = true;
        boolean isStatusCorrect = createNewUserPage.isPageOpen().createUser(firstName, "", age, money, isMale).isStatusMessageCorrect(targetStatusMessage);
        Assertions.assertTrue(isStatusCorrect, "status message should be correct");
    }

    @Test
    @DisplayName("Создание пользователя возрастом меньше 0")
    @Owner("Julia Sinkova")
    public void createUserWithNegativeAge() {
        String targetStatusMessage = "Status: Invalid request data";
        String firstName = "Test123";
        String secondName = "1234567890";
        int age = -1;
        int money = 1;
        boolean isMale = false;
        boolean isStatusCorrect = createNewUserPage.isPageOpen().createUser(firstName, secondName, age, money, isMale).isStatusMessageCorrect(targetStatusMessage);
        Assertions.assertTrue(isStatusCorrect, "status message should be correct");
    }

    @Test
    @DisplayName("Создание пользователя без заполнения поля sex")
    @Owner("Julia Sinkova")
    public void createUserWithoutSex() {
        String targetStatusMessage = "Status: Invalid request data";
        String firstName = "Test123";
        String secondName = "1234567890";
        int age = 150;
        int money = 200;
        boolean isStatusCorrect = createNewUserPage.isPageOpen()
                .fillFirstName(firstName)
                .fillSecondName(secondName)
                .fillAge(age)
                .fillMoney(money)
                .clickPushButton()
                .isStatusMessageCorrect(targetStatusMessage);
        Assertions.assertTrue(isStatusCorrect, "status message should be correct");
    }

    @Test
    @DisplayName("Создание пользователя без заполнения поля money")
    @Owner("Julia Sinkova")
    public void createUserWithoutMoney() {
        String targetStatusMessage = "Status: Invalid request data";
        String firstName = "Test123";
        String secondName = "1234567890";
        int age = -1;
        boolean isMale = true;
        boolean isStatusCorrect = createNewUserPage.isPageOpen()
                .fillFirstName(firstName)
                .fillSecondName(secondName)
                .fillAge(age)
                .selectSex(isMale)
                .clickPushButton()
                .isStatusMessageCorrect(targetStatusMessage);
        Assertions.assertTrue(isStatusCorrect, "status message should be correct");
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
        boolean isStatusMessageCorrect = createNewUserPage.isPageOpen().createUser(firstName, secondName, age, money, isMale)
                .isStatusMessageCorrect(targetStatusMessage);
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
                () -> Assertions.assertTrue(isStatusMessageCorrect, "status message should be correct"),
                () -> Assertions.assertEquals(firstName, finalFirstNameFromDB, "first name from data base should match the one used to create user"),
                () -> Assertions.assertEquals(secondName, finalSecondNameFromDB, "second name from data base should match the one used to create user"),
                () -> Assertions.assertEquals(age, finalAgeFromDB, "age from data base should match the one used to create user"),
                () -> Assertions.assertEquals(money, finalMoneyFromDB, "money from data base should match the one used to create user"),
                () -> Assertions.assertEquals(isMale, finalIsMaleFromDB, "sex from data base should match the one used to create user")

        );
    }
}
