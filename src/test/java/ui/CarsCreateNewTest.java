package ui;

import base.BaseTest;
import model.Car;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsCreateNewTest extends BaseTest {
    private final String STATUS_SUCCESSFUL = "Status: Successfully pushed, code: 201";
    private final String STATUS_INVALID = "Status: Invalid request data";
    private final String STATUS_AXIOS_ERROR = "Status: AxiosError: Request failed with status code 400";

    @BeforeEach
    void setUp() {
        loginPage.isPageOpen()
                .fullAuthorization();
        menu.openCarsCreateNew();
    }

    @Test
    @Order(1)
    @DisplayName("Проверка перехода авторизированным пользователем на страницу Create new")
    public void testAuthorization() {
        carsCreateNew.isOpenPage();
    }

    @Test
    @Order(2)
    @DisplayName("Проверка создания автомобиля")
    public void checkCreateCar() throws SQLException {
        String receivedStatus = carsCreateNew
                .isOpenPage()
                .createNewCar("Diesel", "FiatUs", "Albea", 5000.00)
                .getStatusText();
        String newId = carsCreateNew.getNewId();

        menu.openCarsReadAll();
        String firstID = carsReadAll
                .clickSortById()
                .clickSortById()
                .getFirstId();
        assertAll(
                () -> assertEquals(STATUS_SUCCESSFUL, receivedStatus, "Статус не соответствует ожидаемому"),
                () -> assertEquals(firstID, newId, "Id нового автомобиля не получен")
        );

        Car createdCar = dbConnection.getCarById(newId);
        assertAll(
                () -> assertEquals("Diesel", createdCar.getEngineType(), "Тип двигателя не соответствует ожидаемому"),
                () -> assertEquals("FiatUs", createdCar.getMark(), "Марка не соответствует ожидаемой"),
                () -> assertEquals("Albea", createdCar.getModel(), "Модель не соответствует ожидаемой"),
                () -> assertEquals(5000.0, createdCar.getPrice(), "Цена не соответствует ожидаемой")
        );
    }


    @Test
    @Order(3)
    @DisplayName("Проверка создания автомобиля с пустыми полями")
    public void checkCreateCarWithEmptyFold() {
        assertEquals(carsCreateNew
                .isOpenPage()
                .createNewCar("", "", "", null)
                .getStatusText(), STATUS_INVALID, "Статус не соответствует ожидаемому");
    }

    @Test
    @Order(4)
    @DisplayName("Проверка создания автомобиля с пустым полем Engine Type")
    public void checkCreateCarWithEmptyEngineType() {
        assertEquals(carsCreateNew
                .isOpenPage()
                .createNewCar("", "Fiat", "Albea", 5000.0)
                .getStatusText(), STATUS_INVALID, "Статус не соответствует ожидаемому");
    }

    @Test
    @Order(5)
    @DisplayName("Проверка создания автомобиля с пустым полем Mark")
    public void checkCreateCarWithEmptyMark() {
        assertEquals(carsCreateNew
                .isOpenPage()
                .createNewCar("Diesel", "", "Albea", 5000.0)
                .getStatusText(), STATUS_INVALID, "Статус не соответствует ожидаемому");
    }

    @Test
    @Order(6)
    @DisplayName("Проверка создания автомобиля с пустым полем Model")
    public void checkCreateCarWithEmptyModel() {
        assertEquals(carsCreateNew
                .isOpenPage()
                .createNewCar("Diesel", "Fiat", "", 5000.0)
                .getStatusText(), STATUS_INVALID, "Статус не соответствует ожидаемому");
    }

    @Test
    @Order(7)
    @DisplayName("Проверка создания автомобиля с пустым полем Price")
    public void checkCreateCarWithEmptyPrice() {
        assertEquals(carsCreateNew
                .isOpenPage()
                .createNewCar("Diesel", "Fiat", "Albea", null)
                .getStatusText(), STATUS_INVALID, "Статус не соответствует ожидаемому");
    }


    @Test
    @Order(8)
    @DisplayName("Проверка создания автомобиля с отрицательными числами в поле Price")
    public void checkNegativeNumbersPriceFold() {
        assertEquals(carsCreateNew
                .isOpenPage()
                .createNewCar("Diesel", "Fiat", "Albea", -12.0)
                .getStatusText(), STATUS_INVALID, "Статус не соответствует ожидаемому");
    }

    @Test
    @Order(9)
    @DisplayName("Проверка создания автомобиля с невалидным EngineType")
    public void checkInvalidEngineTypeFold() {
        assertEquals(carsCreateNew
                .isOpenPage()
                .createNewCar("Fiat", "Fiat", "Albea", 12.0)
                .getStatusText(), STATUS_AXIOS_ERROR, "Статус не соответствует ожидаемому");
    }
}
