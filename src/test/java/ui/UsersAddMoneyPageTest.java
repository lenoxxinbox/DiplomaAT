package ui;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersAddMoneyPageTest extends BaseTest{

    @BeforeEach
    public void setUp() {
        loginPage.isPageOpen().fullAuthorization();
        menu.openUsersAddMoney();
    }

    @Test
    @Order(1)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Добавить денег существующему пользователю")
    public void findUserWithCarsExist() {
        String expectedMessage = "Status: Successfully pushed, code: 200";
        usersAddMoneyPage.addMoneyToUser("3", "200");
        String actualMessage = usersAddMoneyPage.statusInfo();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    @Order(2)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Добавить денег несуществующему пользователю")
    public void findUserWithCarsNotExist() {
        String expectedMessage = "Status: AxiosError: Request failed with status code 404";
        usersAddMoneyPage.addMoneyToUser("909090909090","200");
        String actualMessage = usersAddMoneyPage.statusInfo();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    @Order(3)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Ввод невалидных данных в поля User ID и Money")
    public void findUserWithCarsNotValid() {
        String expectedMessage = "Status: Incorrect input data";
        usersAddMoneyPage.addMoneyToUser("-1","0");
        String actualMessage = usersAddMoneyPage.statusInfo();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }
}
