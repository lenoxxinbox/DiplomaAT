package ui;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Проверка сортировки таблицы со всеми пользователями")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersReadAllTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        loginPage
                .isPageOpen()
                .fullAuthorization();
        menu
                .openUsersReadAll();
    }

    @Test
    @Order(1)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки ID по возростанию")
    public void checkSortByIdUp() {
        usersReadAllPage.sortById();
        usersReadAllPage.sortButtonVisible("↑");
        usersReadAllPage.getListForId(true);
    }

    @Test
    @Order(2)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки ID по убыванию")
    public void checkSortByIdDesc() {
        usersReadAllPage.sortById();
        usersReadAllPage.sortById();
        usersReadAllPage.sortButtonVisible("↓");
        usersReadAllPage.getListForId(false);
    }

    @Test
    @Order(3)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки First Name по возрастанию")
    public void checkSortByFirstNameUp() {
        usersReadAllPage.sortByFirstName();
        usersReadAllPage.sortButtonVisible("↑");
        usersReadAllPage.getListForFirstName(true);
    }

    @Test
    @Order(4)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки First Name по убыванию")
    public void checkSortByFirstNameDesc() {
        usersReadAllPage.sortByFirstName();
        usersReadAllPage.sortByFirstName();
        usersReadAllPage.sortButtonVisible("↓");
        usersReadAllPage.getListForFirstName(false);
    }


    @Test
    @Order(5)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Last Name по возрастанию")
    public void checkSortByLastNameUp() {
        usersReadAllPage.sortByLastName();
        usersReadAllPage.sortButtonVisible("↑");
        usersReadAllPage.getListForLastName(true);
    }

    @Test
    @Order(6)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Last Name по убыванию")
    public void checkSortByLastNameDesc() {
        usersReadAllPage.sortByLastName();
        usersReadAllPage.sortByLastName();
        usersReadAllPage.sortButtonVisible("↓");
        usersReadAllPage.getListForLastName(false);
    }

    @Test
    @Order(7)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Age по возрастанию")
    public void checkSortByAgeUp() {
        usersReadAllPage.sortByAge();
        usersReadAllPage.sortButtonVisible("↑");
        usersReadAllPage.getListForAge(true);
    }

    @Test
    @Order(8)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Age по убыванию")
    public void checkSortByAgeDesc() {
        usersReadAllPage.sortByAge();
        usersReadAllPage.sortByAge();
        usersReadAllPage.sortButtonVisible("↓");
        usersReadAllPage.getListForAge(false);
    }

    @Test
    @Order(9)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Sex по возрастанию")
    public void checkSortBySexUp() {
        usersReadAllPage.sortBySex();
        usersReadAllPage.sortButtonVisible("↑");
        usersReadAllPage.getListForSex(true);
    }

    @Test
    @Order(10)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Sex по убыванию")
    public void checkSortSexDesc() {
        usersReadAllPage.sortBySex();
        usersReadAllPage.sortBySex();
        usersReadAllPage.sortButtonVisible("↓");
        usersReadAllPage.getListForSex(false);
    }

    @Test
    @Order(11)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Money по возрастанию")
    public void checkSortByMoneyUp() {
        usersReadAllPage.sortByMoney();
        usersReadAllPage.sortButtonVisible("↑");
        usersReadAllPage.getListForSex(true);
    }

    @Test
    @Order(12)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка сортировки Money по убыванию")
    public void checkSortMoneyDesc() {
        usersReadAllPage.sortByMoney();
        usersReadAllPage.sortByMoney();
        usersReadAllPage.sortButtonVisible("↓");
        usersReadAllPage.getListForMoney(false);
    }

    @Test
    @Order(13)
    @Owner("Lapidus Vyacheslav")
    @DisplayName("Проверка кнопки Reload")
    public void checkReloadButton() {
        String defaultId = usersReadAllPage.getDefaultId();
        assertTrue(usersReadAllPage
                .sortById()
                .reload()
                .isIdCorrect(defaultId), "ID первой строки должен совпадать после перезагрузки");
    }
}
