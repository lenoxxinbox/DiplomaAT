package ui;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettleEvictHousesTest extends BaseTest {

    static final String SUCCESS_MESSAGE = "Status: Successfully pushed, code: 200";
    static final String NOT_FOUND_MESSAGE = "Status: AxiosError: Request failed with status code 404";

    @BeforeEach
    public void inTest() {
        loginPage.fullAuthorization();
        menu.goToSettleEvictHousesMenu();
    }

    @Test
    @DisplayName("Проверка успешного заселения пользователя в дом")
    @Owner("Elena Dmitrienko")
    public void settleHouseTest() {
        assertEquals(settleEvictHousesPage
                .userIDInput("22")
                .houseIDInput("1")
                .settle()
                .push()
                .getResultMessage(), SUCCESS_MESSAGE, "Не удалось заселить пользователя в дом");
    }

    @Test
    @DisplayName("Проверка успешного выселения пользователя из дома")
    @Owner("Elena Dmitrienko")
    public void evictHouseTest() {
        assertEquals(settleEvictHousesPage
                .userIDInput("22")
                .houseIDInput("1")
                .settle()
                .push()
                .getResultMessage(), SUCCESS_MESSAGE, "Не удалось выселить пользователя из дома");
    }

    @Test
    @DisplayName("Проверка сообщения об ошибке при выселении пользователя из несуществующего дома")
    @Owner("Elena Dmitrienko")
    public void evictNotHouseTest() {
        assertEquals(settleEvictHousesPage
                .userIDInput("22")
                .houseIDInput("101010101010")
                .evict()
                .push()
                .getResultMessage(), NOT_FOUND_MESSAGE, "Нет сообщения об ошибке");
    }

    @Test
    @DisplayName("Проверка сообщения об ошибке при заселении несуществующего пользователя в дом")
    @Owner("Elena Dmitrienko")
    public void settleNotUserTest() {
        assertEquals(settleEvictHousesPage
                .userIDInput("101010101010")
                .houseIDInput("1")
                .settle()
                .push()
                .getResultMessage(), NOT_FOUND_MESSAGE, "Нет сообщения об ошибке");
    }
}
