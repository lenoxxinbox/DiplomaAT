package ui;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuySellCarTest extends BaseTest {

    static final String SUCCESS_MESSAGE = "Status: Successfully pushed, code: 200";
    static final String NOT_FOUND_MESSAGE = "Status: AxiosError: Request failed with status code 404";

    @BeforeEach
    public void inTest() {
        loginPage.fullAuthorization();
        menu.goToBuySellCarMenu();
    }

    @Test
    @DisplayName("Проверка успешной покупки автомобиля")
    @Owner("Elena Dmitrienko")
    public void buyCarTest() {
        assertEquals(buySellCarPage
                .userIDInput("14")
                .carIDInput("54")
                .buy()
                .push()
                .getResultMessage(), SUCCESS_MESSAGE, "Не удалось успешно купить автомобиль");
    }

    @Test
    @DisplayName("Проверка успешной продажи автомобиля")
    @Owner("Elena Dmitrienko")
    public void sellCarTest() {
        assertEquals(buySellCarPage
                .userIDInput("14")
                .carIDInput("54")
                .buy()
                .push()
                .getResultMessage(), SUCCESS_MESSAGE, "Не удалось успешно продать автомобиль");
    }

    @Test
    @DisplayName("Проверка сообщения об ошибке при продаже несуществующего автомобиля")
    @Owner("Elena Dmitrienko")
    public void sellNotCarTest() {
        assertEquals(buySellCarPage
                .userIDInput("14")
                .carIDInput("1010101010101")
                .buy()
                .push()
                .getResultMessage(), NOT_FOUND_MESSAGE, "Нет сообщения об ошибке");
    }

    @Test
    @DisplayName("Проверка сообщения об ошибке при продаже автомобиля несуществующим пользователем")
    @Owner("Elena Dmitrienko")
    public void buyNotUserTest() {
        assertEquals(buySellCarPage
                .userIDInput("1010101010101")
                .carIDInput("54")
                .buy()
                .push()
                .getResultMessage(), NOT_FOUND_MESSAGE, "Нет сообщения об ошибке");
    }
}
