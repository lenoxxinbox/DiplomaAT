package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.CreateNewUserPage;
import pages.LoginPage;
import utils.ConfigReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static String BASE_URL = ConfigReader.get("baseURL");
    LoginPage loginPage;
    CreateNewUserPage createNewUserPage;

    @BeforeAll
    public static void setUpBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";  // Это заменяет startMaximized
    }

    @BeforeEach
    public void openBrowser() {
        open(BASE_URL);
        loginPage = new LoginPage();
        createNewUserPage = new CreateNewUserPage();
    }

    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
    }
}