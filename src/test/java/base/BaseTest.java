package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.BuySellCarPage;
import pages.LoginPage;
import pages.SettleEvictHousesPage;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    protected static String baseURl = "http://77.50.236.203:4881/";
    public LoginPage loginPage;
    public BuySellCarPage buySellCarPage;
    public SettleEvictHousesPage settleEvictHousesPage;

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
        settleEvictHousesPage = new SettleEvictHousesPage();
    }
    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
    }
}