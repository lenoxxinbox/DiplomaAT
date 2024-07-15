import base.BaseTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsCreateNewTests extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Переход НЕавторизированным пользователем на страницу Create new")
    public void testUnAuthorization() {
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage();
    }

    @Test
    @Order(2)
    @DisplayName("Переход авторизированным пользователем на страницу Create new")
    public void testAuthorization() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage();
    }

    @Test
    @Order(3)
    @DisplayName("Создание автомобиля")
    public void testCreateCar() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .createNewCar("Diesel", "Fiat", "Albea", "5000");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Successfully pushed, code: 201";
        String newId = carsCreateNew.getNewId();

        menu.openCarsReadAll();
        carsReadAll.clickSortById()
                .clickSortById();
        String firstID = carsReadAll.getFirstId();
        assertAll(
                () -> assertEquals(statusExpected, status),
                () -> assertEquals(firstID, newId)
        );
    }

    @Test
    @Order(4)
    @DisplayName("Создание автомобиля с пустыми полями")
    public void testCreateEmptyFoldCar() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .createNewCar("", "", "", "");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Successfully pushed, code: 201";
        String newId = carsCreateNew.getNewId();

        menu.openCarsReadAll();
        carsReadAll.clickSortById()
                .clickSortById();
        String firstID = carsReadAll.getFirstId();
        assertAll(
                () -> assertEquals(statusExpected, status),
                () -> assertEquals(firstID, newId)
        );
    }

    @Test
    @Order(5)
    @DisplayName("Создание автомобиля с пустым полем Engine Type")
    public void testEmptyEngineType() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("")
                .enterMark("Fiat")
                .enterModel("Largus")
                .enterPrice("1800");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(6)
    @DisplayName("Создание автомобиля с пустым полем Mark")
    public void testEmptyMark() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("Diesel")
                .enterMark("")
                .enterModel("Largus")
                .enterPrice("1800");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(7)
    @DisplayName("Создание автомобиля с пустым полем Model")
    public void testEmptyModel() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("Diesel")
                .enterMark("Fiat")
                .enterModel("")
                .enterPrice("1800");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(8)
    @DisplayName("Создание автомобиля с пустым полем Price")
    public void testEmptyPrice() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("Diesel")
                .enterMark("Fiat")
                .enterModel("Albea")
                .enterPrice("");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(9)
    @DisplayName("Создание автомобиля с недопустимыми символами в поле Price")
    public void testInvalidCharPriceFold() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("Diesel")
                .enterMark("Fiat")
                .enterModel("Albea")
                .enterPrice("///@?#");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(10)
    @DisplayName("Создание автомобиля с буквами в поле Price")
    public void testLettersPriceFold() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("Diesel")
                .enterMark("Fiat")
                .enterModel("Albea")
                .enterPrice("AsdФыв");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(11)
    @DisplayName("Создание автомобиля с отрицательными числами в поле Price")
    public void testNegativeNumbersPriceFold() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("Diesel")
                .enterMark("Fiat")
                .enterModel("Albea")
                .enterPrice("-12");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(12)
    @DisplayName("Создание автомобиля с отрицательными числами в поле Price")
    public void testInvalidEngineTypeFold() {
        loginPage.login("user@pflb.ru", "user");
        menu.openCarsCreateNew();
        carsCreateNew.isOpenPage()
                .enterEngineType("Fiat")
                .enterMark("Fiat")
                .enterModel("Albea")
                .enterPrice("12");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: AxiosError: Request failed with status code 400";
        assertEquals(statusExpected, status);
    }

}
