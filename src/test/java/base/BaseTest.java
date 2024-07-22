package base;

import com.codeborne.selenide.Configuration;
import dbconnection.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;
import pages.MainPage;
import pages.cars.CarsCreateNew;
import pages.cars.CarsReadAll;
import pages.houses.HousesReadAll;
import utils.ConfigReader;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static String DB_URL = ConfigReader.get("dbUrl");
    protected static String DB_USER = ConfigReader.get("dbUser");
    protected static String DB_PASSWORD = ConfigReader.get("dbPassword");
    protected static String BASE_URL = ConfigReader.get("baseURL");
    protected static MainPage menu;
    protected static LoginPage loginPage;
    protected static CarsReadAll carsReadAll;
    protected static CarsCreateNew carsCreateNew;
    protected static HousesReadAll housesReadAll;
    protected static DBConnection dbConnection;

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
        carsReadAll = new CarsReadAll();
        carsCreateNew = new CarsCreateNew();
        housesReadAll = new HousesReadAll();
        dbConnection = new DBConnection();
        try {
            dbConnection.connect(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
        try {
            dbConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

