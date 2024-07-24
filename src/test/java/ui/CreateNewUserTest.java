package ui;

import api_service.ApiConnection;
import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Проверка создания пользователя")
public class CreateNewUserTest extends BaseTest {
    public static final String INVALID_REQUEST_STATUS = "Status: Invalid request data";
    public static final String SUCCESSFUL_REQUEST_STATUS = "Status: Successfully pushed, code: 201";
    public static final String FIRST_NAME_TEXT = "Test";
    public static final String SECOND_NAME_TEXT = "User123";
    public static final int AGE_NUM = 25;
    public static final int NEGATIVE_AGE_NUM = -1;
    public static final int MONEY_NUM = 1000;
    public static final boolean IS_MALE = true;
    private final static String USERNAME = ConfigReader.get("username");
    private final static String PASSWORD = ConfigReader.get("password");
    ApiConnection apiConnection = new ApiConnection(API_URL);

    @BeforeEach
    public void login() {
        loginPage.isPageOpen().fullAuthorization();
        menu.openCreateNewUserPage();
    }

    @Test
    @DisplayName("Проверка создания пользователя с пустыми полями")
    @Owner("Julia Sinkova")
    public void createUserWithEmptyFields() {
        boolean isStatusCorrect = createNewUserPage
                .isPageOpen()
                .clickPushButton()
                .isStatusMessageCorrect(INVALID_REQUEST_STATUS);
        assertTrue(isStatusCorrect, "Статус должен быть корректным");
    }

    @Test
    @DisplayName("Проверка создания пользователя без заполнения поля first_name")
    @Owner("Julia Sinkova")
    public void createUserWithoutFirstName() {
        boolean isStatusCorrect = createNewUserPage
                .isPageOpen()
                .createUser("", SECOND_NAME_TEXT, AGE_NUM, MONEY_NUM, IS_MALE)
                .isStatusMessageCorrect(INVALID_REQUEST_STATUS);
        assertTrue(isStatusCorrect, "Статус должен быть корректным");
    }

    @Test
    @DisplayName("Проверка создания пользователя без заполнения поля second_name")
    @Owner("Julia Sinkova")
    public void createUserWithoutSecondName() {
        boolean isStatusCorrect = createNewUserPage
                .isPageOpen()
                .createUser(FIRST_NAME_TEXT, "", AGE_NUM, MONEY_NUM, IS_MALE)
                .isStatusMessageCorrect(INVALID_REQUEST_STATUS);
        assertTrue(isStatusCorrect, "Статус должен быть корректным");
    }

    @Test
    @DisplayName("Проверка создания пользователя возрастом меньше 0")
    @Owner("Julia Sinkova")
    public void createUserWithNegativeAge() {
        boolean isStatusCorrect = createNewUserPage
                .isPageOpen()
                .createUser(FIRST_NAME_TEXT, SECOND_NAME_TEXT, NEGATIVE_AGE_NUM, MONEY_NUM, IS_MALE)
                .isStatusMessageCorrect(INVALID_REQUEST_STATUS);
        assertTrue(isStatusCorrect, "Статус должен быть корректным");
    }

    @Test
    @DisplayName("Проверка создания пользователя без заполнения поля sex")
    @Owner("Julia Sinkova")
    public void createUserWithoutSex() {
        boolean isStatusCorrect = createNewUserPage
                .isPageOpen()
                .fillFirstName(FIRST_NAME_TEXT)
                .fillSecondName(SECOND_NAME_TEXT)
                .fillAge(AGE_NUM)
                .fillMoney(MONEY_NUM)
                .clickPushButton()
                .isStatusMessageCorrect(INVALID_REQUEST_STATUS);
        assertTrue(isStatusCorrect, "Статус должен быть корректным");
    }

    @Test
    @DisplayName("Проверка создания пользователя без заполнения поля money")
    @Owner("Julia Sinkova")
    public void createUserWithoutMoney() {
        boolean isStatusCorrect = createNewUserPage
                .isPageOpen()
                .fillFirstName(FIRST_NAME_TEXT)
                .fillSecondName(SECOND_NAME_TEXT)
                .fillAge(AGE_NUM)
                .selectSex(IS_MALE)
                .clickPushButton()
                .isStatusMessageCorrect(INVALID_REQUEST_STATUS);
        assertTrue(isStatusCorrect, "Статус должен быть корректным");
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    @Owner("Julia Sinkova")
    public void createUser() {
        boolean isStatusMessageCorrect = createNewUserPage
                .isPageOpen()
                .createUser(FIRST_NAME_TEXT, SECOND_NAME_TEXT, AGE_NUM, MONEY_NUM, IS_MALE)
                .isStatusMessageCorrect(SUCCESSFUL_REQUEST_STATUS);
        String id = createNewUserPage.getNewUserId();

        User userFromDB = null;
        try {
            userFromDB = dbConnection.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User finalUserFromDB = userFromDB;
        // удаление пользователя через API
        apiConnection.authorize(USERNAME, PASSWORD);
        int code = apiConnection.deleteUser(Integer.parseInt(id)).getStatusCode();

        assertAll(() -> assertTrue(isStatusMessageCorrect, "status message should be correct"),
                () -> assertEquals(FIRST_NAME_TEXT, finalUserFromDB.getFirstName(), "Значения поля first_name при создании и в БД должны совпадать"),
                () -> assertEquals(SECOND_NAME_TEXT, finalUserFromDB.getSecondName(), "Значения поля second_name при создании и в БД должны совпадать"),
                () -> assertEquals(AGE_NUM, finalUserFromDB.getAge(), "Значения поля age при создании и в БД должны совпадать"),
                () -> assertEquals(MONEY_NUM, finalUserFromDB.getMoney(), "Значения поля money при создании и в БД должны совпадать"),
                () -> assertEquals(IS_MALE, finalUserFromDB.isMale(), "Значения поля sex при создании и в БД должны совпадать"),
                () -> assertEquals(code, 204, "Созданный пользователь должен быть удален")
        );
    }
}
