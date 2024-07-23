package ui;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Проверка поиска пользователей с машинами")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersReadUserWithCarsPageTest extends BaseTest {

    String expectedMessageOk = "Status: 200 ok";
    String expectedMessageUserNotFound = "Status: 204 user not found";

    @BeforeEach
    public void setUp() {
        loginPage
                .isPageOpen()
                .fullAuthorization();
        menu
                .openUsersReadUserWithCars();
    }

    @Test
    @Order(1)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Поиск существующего пользователя")
    public void findUserWithCarsExist() {
        usersReadUserWithCarsPage.findUserWithCars("3");
        String actualMessage = usersReadUserWithCarsPage.statusInfo();
        assertEquals(actualMessage, expectedMessageOk);
    }

    @Test
    @Order(2)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Поиск несуществующего пользователя")
    public void findUserWithCarsNotExist() {
        usersReadUserWithCarsPage.findUserWithCars("9999999999999");
        String actualMessage = usersReadUserWithCarsPage.statusInfo();
        assertEquals(actualMessage, expectedMessageUserNotFound);
    }
}
