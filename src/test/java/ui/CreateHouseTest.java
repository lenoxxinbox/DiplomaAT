package ui;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import model.House;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.CreateHousePage.id;

public class CreateHouseTest extends BaseTest {

    public static final String SUCCESSFUL_REQUEST_STATUS = "Status: Successfully pushed, code: 201";
    public static final String INVALID_REQUEST_STATUS = "Status: Invalid input data";
    public static final String FLOORS_COUNT = "2";
    public static final String PARKING_PLACES = "14";
    public static final String PLACES_WARM_AND_COVERED = "2";
    public static final String PLACES_WARM_AND_NO_COVERED = "3";
    public static final String PLACES_NO_WARM_AND_COVERED = "4";
    public static final String PLACES_NO_WARM_AND_NO_COVERED = "5";
    public static final String PRICE = "9365.33";
    public static final String EMPTY = "";


    @BeforeEach
    public void inTest() {
        loginPage.fullAuthorization();
        menu.goCreateHousePage();
    }

    @Test
    @DisplayName("Проверка успешного создания дома")
    @Owner("Кузнецов Александр")
    public void createHouse() {
        assertEquals(createHousePage
                .createHouse(FLOORS_COUNT, PRICE, PLACES_WARM_AND_COVERED, PLACES_WARM_AND_NO_COVERED, PLACES_NO_WARM_AND_COVERED, PLACES_NO_WARM_AND_NO_COVERED)
                .getCreateStatus(), SUCCESSFUL_REQUEST_STATUS, "Сообщение не соответствует");
        House houseFromDB = null;
        try {
            houseFromDB = dbConnection.getHouseById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        House finalHouseFromDB = houseFromDB;
        assertAll(
                () -> assertEquals(FLOORS_COUNT, finalHouseFromDB.getFloorCount(), "Значения поля floor_count при создании и в БД должны совпадать"),
                () -> assertEquals(PARKING_PLACES, finalHouseFromDB.getParkingPlaces(), "Значения поля places_count при создании и в БД должны совпадать"),
                () -> assertEquals(PRICE, finalHouseFromDB.getPrice(), "Значения поля price при создании и в БД должны совпадать")
        );
    }

    @Test
    @DisplayName("Проверка создания дома с пустыми полями")
    @Owner("Кузнецов Александр")
    public void createHouseNoData() {
        assertEquals(createHousePage
                .createHouse(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)
                .getCreateStatus(), INVALID_REQUEST_STATUS, "Сообщение не соответствует");
    }
}
