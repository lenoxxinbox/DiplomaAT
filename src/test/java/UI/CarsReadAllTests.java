package UI;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsReadAllTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        loginPage.fullAuthorization();
        menu.openCarsReadAll();
    }

    @Test
    @Order(1)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки ID по возрастанию")
    public void checkSortIdUp() {
        carsReadAll.clickSortById()
                .assertSortButtonVisible("↑")
                .getListForId(true);
    }

    @Test
    @Order(2)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки ID по убыванию")
    public void checkSortIdDesc() {
        carsReadAll.clickSortById()
                .clickSortById()
                .assertSortButtonVisible("↓")
                .getListForId(false);
    }

    @Test
    @Order(3)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Engine Type по возрастанию")
    public void checkSortEngineTypeUp() {
        carsReadAll.clickSortByEngineType()
                .assertSortButtonVisible("↑")
                .getListForEngineType(true);
    }

    @Test
    @Order(4)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Engine Type по убыванию")
    public void checkSortEngineTypeDesc() {
        carsReadAll.clickSortByEngineType()
                .clickSortByEngineType()
                .assertSortButtonVisible("↓")
                .getListForEngineType(false);
    }

    @Test
    @Order(5)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Mark по возрастанию")
    public void checkSortMarkUp() {
        carsReadAll.clickSortByMark()
                .assertSortButtonVisible("↑")
                .getListForMark(true);
    }

    @Test
    @Order(6)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Mark по убыванию")
    public void checkSortMarkDesc() {
        carsReadAll.clickSortByMark()
                .clickSortByMark()
                .assertSortButtonVisible("↓")
                .getListForMark(false);
    }

    @Test
    @Order(7)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Model по возрастанию")
    public void checkSortModelUp() {
        carsReadAll.clickSortByModel()
                .assertSortButtonVisible("↑")
                .getListForModel(true);
    }

    @Test
    @Order(8)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Model по убыванию")
    public void checkSortModelDesc() {
        carsReadAll.clickSortByModel()
                .clickSortByModel()
                .assertSortButtonVisible("↓")
                .getListForModel(false);
    }

    @Test
    @Order(9)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Price по возрастанию")
    public void checkSortPriceUp() {
        carsReadAll.clickSortByPrice()
                .assertSortButtonVisible("↑")
                .getListForPrice(true);
    }

    @Test
    @Order(10)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка сортировки Price по убыванию")
    public void checkSortPriceDesc() {
        carsReadAll.clickSortByPrice()
                .clickSortByPrice()
                .assertSortButtonVisible("↓")
                .getListForPrice(false);
    }

    @Test
    @Order(11)
    @Owner("Kotelin Kirill")
    @DisplayName("Проверка кнопки Reload")
    public void testReloadButton() {
        String initialFirstRowId = carsReadAll.getFirstId();
        carsReadAll.clickSortById()
                .clickReloadButton();
        boolean isIdSame = carsReadAll.isIdCorrect(initialFirstRowId);
        assertTrue(isIdSame, "The first row ID should match after reload");
    }
}
