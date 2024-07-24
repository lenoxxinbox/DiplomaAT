package ui;

import api_service.ApiConnection;
import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import io.qameta.allure.Feature;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import utils.ConfigReader;

@Feature("Проверка добавления денег пользователю")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersAddMoneyPageTest extends BaseTest {
    private final static String USERNAME = ConfigReader.get("username");
    private final static String PASSWORD = ConfigReader.get("password");
    public static final String FIRST_NAME_TEXT = "Test";
    public static final String SECOND_NAME_TEXT = "User123";
    public static final int AGE_NUM = 25;
    public static final int NEGATIVE_AGE_NUM = -1;
    public static final int MONEY_NUM = 1000;
    public static final boolean IS_MALE = true;

    public static final String INVALID_REQUEST_STATUS = "Status: Invalid request data";
    public static final String SUCCESSFUL_REQUEST_STATUS = "Status: Successfully pushed, code: 201";

    public static final String expectedMessageSuccess = "Status: Successfully pushed, code: 200";
    public static final String expectedMessageError = "Status: AxiosError: Request failed with status code 404";
    public static final String expectedMessageIncorrectData = "Status: Incorrect input data";
    ApiConnection apiConnection = new ApiConnection(API_URL);

    @BeforeEach
    public void setUp() {
        loginPage
                .isPageOpen()
                .fullAuthorization();
    }

    @Test
    @Order(1)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Добавление денег только что созданному пользователю")
    public void addMoneyToUser() {
        menu.openCreateNewUserPage();
        boolean isStatusMessageCorrect = createNewUserPage
                .isPageOpen()
                .createUser(FIRST_NAME_TEXT, SECOND_NAME_TEXT, AGE_NUM, MONEY_NUM, IS_MALE)
                .isStatusMessageCorrect(SUCCESSFUL_REQUEST_STATUS);
        String id = createNewUserPage.getNewUserId();

        // Добавление денег пользователю
        menu.openUsersAddMoney();
        usersAddMoneyPage.addMoneyToUser(id, "200");
        String actualMessage = usersAddMoneyPage.statusInfo();

        // Проверка изменения баланса пользователя
        double initialMoney = MONEY_NUM;
        double additionalMoney = 200;
        double expectedMoney = initialMoney + additionalMoney;

        try {
            double actualMoney = dbConnection.getUserMoneyById(id);
            assertEquals(expectedMoney, actualMoney, "Баланс пользователя после добавления денег должен быть равен " + expectedMoney);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Удаление пользователя через API
        apiConnection.authorize(USERNAME, PASSWORD);
        int code = apiConnection.deleteUser(Integer.parseInt(id)).getStatusCode();

        assertAll(() -> assertTrue(isStatusMessageCorrect, "status message should be correct"),
                () -> assertEquals(actualMessage, expectedMessageSuccess),
                () -> assertEquals(code, 204)
        );
    }

    @Test
    @Order(2)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Добавить денег существующему пользователю")
    public void findUserWithCarsExist() {
        menu.openUsersAddMoney();
        usersAddMoneyPage.addMoneyToUser("3", "200");
        String actualMessage = usersAddMoneyPage.statusInfo();
        assertEquals(actualMessage, expectedMessageSuccess);
    }

    @Test
    @Order(3)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Добавить денег несуществующему пользователю")
    public void findUserWithCarsNotExist() {
        menu.openUsersAddMoney();
        usersAddMoneyPage.addMoneyToUser("909090909090","200");
        String actualMessage = usersAddMoneyPage.statusInfo();
        assertEquals(actualMessage, expectedMessageError);
    }

    @Test
    @Order(4)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Ввод невалидных данных в поля User ID и Money")
    public void findUserWithCarsNotValid() {
        menu.openUsersAddMoney();
        usersAddMoneyPage.addMoneyToUser("-1","0");
        String actualMessage = usersAddMoneyPage.statusInfo();
        assertEquals(actualMessage, expectedMessageIncorrectData);
    }
}
