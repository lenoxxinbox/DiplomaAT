package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.BuySellCarPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    protected static String baseURl = "http://77.50.236.203:4881/";
    public LoginPage loginPage;
    public BuySellCarPage buySellCarPage;

    @BeforeAll
    public static void setUpBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";  // Это заменяет startMaximized
    }

    @BeforeEach
    public void openBrowser() {
        open(baseURl);
        loginPage = new LoginPage();
        buySellCarPage = new BuySellCarPage();
    }
    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
    }
}