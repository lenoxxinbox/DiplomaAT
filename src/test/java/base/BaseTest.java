package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.Cars.CarsCreateNew;
import pages.Cars.CarsReadAll;
import pages.LoginPage;
import pages.MainPage;
import utils.ConfigReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static String BASE_URL = ConfigReader.get("baseURL");
    protected static MainPage menu;
    protected static LoginPage loginPage;
    protected static CarsReadAll carsReadAll;
    protected static CarsCreateNew carsCreateNew;

    @BeforeAll
    public static void setUpBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";  // Это заменяет startMaximized
    }

    @BeforeEach
    public void openBrowser() {
        open(BASE_URL);
        loginPage = new LoginPage();
        carsReadAll = new CarsReadAll();
        carsCreateNew = new CarsCreateNew();
        menu = new MainPage();
    }

    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
    }
}