package ui;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuySellCarTest extends BaseTest {

    @BeforeEach
    public void inTest() {
        loginPage.fullAuthorization();
    }

    @Test
    @DisplayName("Успешная покупка автомобиля")
    @Owner("Elena Dmitrienko")
    public void buyCarTest() {
        String expectedMessage = "Status: Successfully pushed, code: 200";
        String actualMessage = buySellCarPage.goToBuySellCarMenu().
                userIDInput("14").
                carIDInput("54").
                buy().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Успешная продажа автомобиля")
    @Owner("Elena Dmitrienko")
    public void sellCarTest() {
        String expectedMessage = "Status: Successfully pushed, code: 200";
        String actualMessage = buySellCarPage.goToBuySellCarMenu().
                userIDInput("14").
                carIDInput("54").
                sell().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Продажа несуществующего автомобиля")
    @Owner("Elena Dmitrienko")
    public void sellNotCarTest() {
        String expectedMessage = "Status: AxiosError: Request failed with status code 404";
        String actualMessage = buySellCarPage.goToBuySellCarMenu().
                userIDInput("14").
                carIDInput("101010101010").
                sell().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Продажа автомобиля несуществующим пользователем")
    @Owner("Elena Dmitrienko")
    public void buyNotUserTest() {
        String expectedMessage = "Status: AxiosError: Request failed with status code 404";
        String actualMessage = buySellCarPage.goToBuySellCarMenu().
                userIDInput("101010101010").
                carIDInput("54").
                buy().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}