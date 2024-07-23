package ui;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.houses.CreateHousePage.id;

public class FindHouseTest extends BaseTest {
    public static final String SUCCESS_FIND = "Status: 200 ok";
    public static final String NOT_FIND = "Status: 204 house not found";
    public static final String FLOORS_COUNT = "2";
    public static final String ID_NOT_FOUND = "9884324265";
    public static final String PLACES_WARM_AND_COVERED = "2";
    public static final String PLACES_WARM_AND_NO_COVERED = "3";
    public static final String PLACES_NO_WARM_AND_COVERED = "4";
    public static final String PLACES_NO_WARM_AND_NO_COVERED = "5";
    public static final String PRICE = "9365.33";

    @BeforeEach
    public void inTest() {
        loginPage.fullAuthorization();
    }

    @Test
    @DisplayName("Проверка поиска существующего дома")
    @Owner("Кузнецов Александр")
    public void findHouse() {
        createHousePage
                .goCreateHouse()
                .createHouse(FLOORS_COUNT, PRICE, PLACES_WARM_AND_COVERED, PLACES_WARM_AND_NO_COVERED, PLACES_NO_WARM_AND_COVERED, PLACES_NO_WARM_AND_NO_COVERED);
        assertEquals(findHousePage
                .goFindHouse()
                .findHouse(id)
                .getFindStatus(), SUCCESS_FIND, "Сообщение не соответствует");
    }

    @Test
    @DisplayName("Проверка поиска несуществующего дома")
    @Owner("Кузнецов Александр")
    public void findHouseNoFind() {
        assertEquals(findHousePage
                .goFindHouse()
                .findHouse(ID_NOT_FOUND)
                .getFindStatus(), NOT_FIND, "Сообщение не соответствует");
    }
}
