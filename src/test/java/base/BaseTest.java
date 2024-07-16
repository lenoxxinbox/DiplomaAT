package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.CreateNewUserPage;
import pages.LoginPage;
import pages.MainPage;
import utils.ConfigReader;
import utils.DBConnection;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static String BASE_URL = ConfigReader.get("baseURL");
    protected static DBConnection dbConnection;
    CreateNewUserPage createNewUserPage;
    LoginPage loginPage;
    MainPage menu;

    @BeforeAll
    public static void setUpBeforeClass() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        dbConnection = new DBConnection();
    }

    @BeforeEach
    public void openBrowser() {
        open(BASE_URL);
        loginPage = new LoginPage();
        createNewUserPage = new CreateNewUserPage();
        menu = new MainPage();

    }

    @AfterEach
    public void tearDownAfterClass() throws SQLException {
        closeWebDriver();
        dbConnection.disconnect();
    }
}