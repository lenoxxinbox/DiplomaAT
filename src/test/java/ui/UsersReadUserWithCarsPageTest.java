package ui;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersReadUserWithCarsPageTest extends BaseTest{

    @BeforeEach
    public void setUp() {
        loginPage.isPageOpen().fullAuthorization();
        menu.openUsersReadUserWithCars();
    }

    @Test
    @Order(1)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Поиск существующего пользователя")
    public void findUserWithCarsExist() {
        String expectedMessage = "Status: 200 ok";
        usersReadUserWithCarsPage.findUserWithCars("3");
        String actualMessage = usersReadUserWithCarsPage.statusInfo();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    @Order(2)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Поиск несуществующего пользователя")
    public void findUserWithCarsNotExist() {
        String expectedMessage = "Status: 204 user not found";
        usersReadUserWithCarsPage.findUserWithCars("909090909090");
        String actualMessage = usersReadUserWithCarsPage.statusInfo();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }
}
