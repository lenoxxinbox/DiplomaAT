package ui;

import base.BaseTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsCreateNewTests extends BaseTest {

    @BeforeEach
    void setUp() {
        loginPage.isPageOpen().fullAuthorization();
        menu.openCarsCreateNew();
    }

    @Test
    @Order(1)
    @DisplayName("Переход авторизированным пользователем на страницу Create new")
    public void testAuthorization() {
        carsCreateNew.isOpenPage();
    }

    @Test
    @Order(2)
    @DisplayName("Создание автомобиля")
    public void testCreateCar() {
        carsCreateNew.isOpenPage().createNewCar("Diesel", "Fiat", "Albea", "5000");
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
    @Order(3)
    @DisplayName("Создание автомобиля с пустыми полями")
    public void testCreateEmptyFoldCar() {
        carsCreateNew.isOpenPage().createNewCar("", "", "", "");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(4)
    @DisplayName("Создание автомобиля с пустым полем Engine Type")
    public void testEmptyEngineType() {
        carsCreateNew.isOpenPage().createNewCar("", "Fiat", "Albea", "5000");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(5)
    @DisplayName("Создание автомобиля с пустым полем Mark")
    public void testEmptyMark() {
        carsCreateNew.isOpenPage().createNewCar("Diesel", "", "Albea", "5000");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(6)
    @DisplayName("Создание автомобиля с пустым полем Model")
    public void testEmptyModel() {
        carsCreateNew.isOpenPage().createNewCar("Diesel", "Fiat", "", "5000");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(7)
    @DisplayName("Создание автомобиля с пустым полем Price")
    public void testEmptyPrice() {
        carsCreateNew.isOpenPage().createNewCar("Diesel", "Fiat", "Albea", "");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(8)
    @DisplayName("Создание автомобиля с недопустимыми символами в поле Price")
    public void testInvalidCharPriceFold() {
        carsCreateNew.isOpenPage().createNewCar("Diesel", "Fiat", "Albea", "/?@#$");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(9)
    @DisplayName("Создание автомобиля с буквами в поле Price")
    public void testLettersPriceFold() {
        carsCreateNew.isOpenPage().createNewCar("Diesel", "Fiat", "Albea", "AsdФыв");
        String status = carsCreateNew.getStatusText();
        String statusExpected = "Status: Invalid request data";
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(10)
    @DisplayName("Создание автомобиля с отрицательными числами в поле Price")
    public void testNegativeNumbersPriceFold() {
        carsCreateNew.isOpenPage().createNewCar("Diesel", "Fiat", "Albea", "-12");
        String statusExpected = "Status: Invalid request data";
        String status = carsCreateNew.getStatusText();
        assertEquals(statusExpected, status);
    }

    @Test
    @Order(11)
    @DisplayName("Создание автомобиля с невалидным EngineType")
    public void testInvalidEngineTypeFold() {
        carsCreateNew.isOpenPage().createNewCar("Fiat", "Fiat", "Albea", "12");
        String statusExpected = "Status: AxiosError: Request failed with status code 400";
        String status = carsCreateNew.getStatusText();
        assertEquals(statusExpected, status);
    }

//    @Nested
//    class NoBeforeEachTests {
//        @Test
//        @Order(1)
//        @DisplayName("Переход НЕавторизированным пользователем на страницу Create new")
//        public void testUnAuthorization() {
//            menu.openCarsCreateNew();
//            carsCreateNew.isOpenPage();
//        }
//    }
}
