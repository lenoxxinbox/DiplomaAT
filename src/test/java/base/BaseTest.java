package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import utils.ConfigReader;

import pages.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static String BASE_URL = ConfigReader.get("baseURL");
    protected static MainPage menu;
    protected static LoginPage loginPage;

    protected static UsersAddMoneyPage usersAddMoneyPage;

    protected static UsersReadAllPage usersReadAllPage;
    protected static UsersReadUserWithCarsPage usersReadUserWithCarsPage;

    @BeforeAll
    public static void setUpBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    public void openBrowser() {
        open(BASE_URL);
        menu = new MainPage();
        loginPage = new LoginPage();
        usersAddMoneyPage = new UsersAddMoneyPage();
        usersReadAllPage = new UsersReadAllPage();
        usersReadUserWithCarsPage = new UsersReadUserWithCarsPage();
    }

    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
    }
}