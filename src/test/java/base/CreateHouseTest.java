package base;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateHouseTest extends BaseTest {
    @BeforeEach
    public void inTest() {
        loginPage.isPageOpen().login("user@pflb.ru", "user");
        Selenide.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Успешное создание дома")
    @Owner("Кузнецов Александр")
    public void createHouse() {
        String expectedMessage = "Status: Successfully pushed, code: 201";
        String actualMessage = createHousePage.goCreateHouse().createHouse("3", "21000", "2", "3", "4", "5").getCreateStatus();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    @DisplayName("Создание дома с пустыми полями")
    @Owner("Кузнецов Александр")
    public void createHouseNoData() {
        String expectedMessage = "Status: Invalid input data";
        String actualMessage = createHousePage.goCreateHouse().createHouse("", "", "", "", "", "").getCreateStatus();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }
}
