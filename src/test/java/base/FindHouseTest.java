package base;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreateHousePage;

public class FindHouseTest extends BaseTest {
    @BeforeEach
    public void inTest() {
        loginPage.isPageOpen().login("user@pflb.ru", "user");
        Selenide.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Поиск существующего дома")
    @Owner("Кузнецов Александр")
    public void findHouse() {
        String expectedMessage = "Status: 200 ok";
        createHousePage.goCreateHouse().createHouse("3", "21000", "2", "3", "4", "5");
        String actualMessage = findHousePage.goFindHouse().findHouse(CreateHousePage.id).getFindStatus();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    @DisplayName("Поиск несуществующего дома")
    @Owner("Кузнецов Александр")
    public void findHouseNoFind() {
        String expectedMessage = "Status: 204 house not found";
        String actualMessage = findHousePage.goFindHouse().findHouse("17").getFindStatus();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }
}
