package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static String baseUSRl = "http://77.50.236.203:4881/";

    @BeforeAll
    public static void setUpBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";  // Это заменяет startMaximized
    }

    @BeforeEach
    public void openBrowser() {
        open(baseUSRl);
    }

    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
    }
}