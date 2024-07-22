package api;

import base.BaseTest;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCRUDTest extends BaseTest {
    private final static String USERNAME = ConfigReader.get("username");
    private final static String PASSWORD = ConfigReader.get("password");
    private static final User USER_CREATE = new User("Julia", "Testova", 100, 5000, false);
    private static final User USER_EDIT = new User("qwerty", "test", 2, 7, false);
    static int userId = 0;

    @BeforeEach
    public void setUp() {
        apiConnection.authorize(USERNAME, PASSWORD);
    }
//     Тесты нужно запускать друг за другом, так как для редактирования и удаления используется пользователь
//     созданный в тесте createUser

    @Test
    @DisplayName("Проверка создания пользователя через API")
    @Owner("Julia Sinkova")
    @Order(1)
    public void createUser() {
        Response postResponse = apiConnection.createUser(USER_CREATE);
        int postResponseStatus = postResponse.getStatusCode();
        userId = postResponse.jsonPath().getInt("id");
        Response getResponse = apiConnection.getUser(userId);
        int getResponseStatus = getResponse.getStatusCode();
        String firstName = getResponse.jsonPath().getString("firstName");
        String secondName = getResponse.jsonPath().getString("secondName");
        int age = getResponse.jsonPath().getInt("age");
        double money = getResponse.jsonPath().getDouble("money");
        boolean isMale = getResponse.jsonPath().getBoolean("sex");
        assertAll(
                () -> assertEquals(201, postResponseStatus, "Статус код запроса на создание пользователя должен быть корректным"),
                () -> assertEquals(200, getResponseStatus, "Статус код запроса на получение пользователя должен быть корректным"),
                () -> assertEquals(USER_CREATE.getFirstName(), firstName, "Поле firstName при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_CREATE.getSecondName(), secondName, "Поле secondName при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_CREATE.getAge(), age, "Поле age при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_CREATE.getMoney(), money, "Поле money при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_CREATE.isMale(), isMale, "Поле sex при создании и при получении из api должны совпадать")

        );
    }

    @Test
    @DisplayName("Проверка редактирования пользователя через API")
    @Owner("Julia Sinkova")
    @Order(2)
    public void editUser() {
        Response editResponse = apiConnection.editUser(userId, USER_EDIT);
        int editResponseStatus = editResponse.getStatusCode();
        Response getResponse = apiConnection.getUser(userId);
        int getResponseStatus = getResponse.getStatusCode();
        String firstName = getResponse.jsonPath().getString("firstName");
        String secondName = getResponse.jsonPath().getString("secondName");
        int age = getResponse.jsonPath().getInt("age");
        double money = getResponse.jsonPath().getDouble("money");
        String sex = getResponse.jsonPath().getString("sex");
        assertAll(
                () -> assertEquals(202, editResponseStatus, "Статус код запроса на редактирование пользователя должен быть корректным"),
                () -> assertEquals(200, getResponseStatus, "Статус код запроса на получение пользователя должен быть корректным"),
                () -> assertEquals(USER_EDIT.getFirstName(), firstName, "Поле firstName при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_EDIT.getSecondName(), secondName, "Поле secondName при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_EDIT.getAge(), age, "Поле age при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_EDIT.getMoney(), money, "Поле money при создании и при получении из api должны совпадать"),
                () -> assertEquals(USER_EDIT.getSex(), sex, "Поле sex при создании и при получении из api должны совпадать")

        );

    }

    @Test
    @DisplayName("Проверка удаления пользователя через API")
    @Owner("Julia Sinkova")
    @Order(3)
    public void deleteUser() {
        Response deleteResponse = apiConnection.deleteUser(userId);
        int deleteResponseStatus = deleteResponse.getStatusCode();
        Response getResponse = apiConnection.getUser(userId);
        int getResponseStatus = getResponse.getStatusCode();
        assertAll(
                () -> assertEquals(204, deleteResponseStatus, "Статус код запроса на удаление пользователя должен быть корректным"),
                () -> assertEquals(204, getResponseStatus, "Статус код запроса на поиск удаленного пользователя должен быть 204")

        );

    }
}
