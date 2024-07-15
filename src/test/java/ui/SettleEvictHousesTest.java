package ui;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SettleEvictHousesTest extends BaseTest {
    @BeforeEach
    public void inTest() {
        loginPage.isPageOpen().login();
        Selenide.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Успешное заселение в дом")
    @Owner("Elena Dmitrienko")
    public void settleHouse() {
        String expectedMessage = "Status: Successfully pushed, code: 200";
        String actualMessage = settleEvictHousesPage.goToSettleEvictHousesMenu().
                userIDInput("22").
                houseIDInput("1").
                settle().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Успешное выселение из дома")
    @Owner("Elena Dmitrienko")
    public void evictHouse() {
        String expectedMessage = "Status: Successfully pushed, code: 200";
        String actualMessage = settleEvictHousesPage.goToSettleEvictHousesMenu().
                userIDInput("22").
                houseIDInput("1").
                evict().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Выселение из несуществующего дома")
    @Owner("Elena Dmitrienko")
    public void evictNotHouse() {
        String expectedMessage = "Status: AxiosError: Request failed with status code 404";
        String actualMessage = settleEvictHousesPage.goToSettleEvictHousesMenu().
                userIDInput("22").
                houseIDInput("101010101010").
                evict().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Заселение в дом несуществующим пользователем")
    @Owner("Elena Dmitrienko")
    public void settleNotUser() {
        String expectedMessage = "Status: AxiosError: Request failed with status code 404";
        String actualMessage = settleEvictHousesPage.goToSettleEvictHousesMenu().
                userIDInput("101010101010").
                houseIDInput("1").
                settle().
                push().
                getResultMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
