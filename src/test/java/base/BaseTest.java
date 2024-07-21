package base;

import api_services.ApiConnection;
import com.codeborne.selenide.Configuration;
import db.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.*;
import pages.Cars.CarsCreateNew;
import pages.Cars.CarsReadAll;
import utils.ConfigReader;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    protected static String BASE_URL = ConfigReader.get("baseURL");
    protected static String DB_URL = ConfigReader.get("dbUrl");
    protected static String DB_USER = ConfigReader.get("dbUser");
    protected static String DB_PASSWORD = ConfigReader.get("dbPassword");
    protected static String API_URL = ConfigReader.get("apiUrl");
    protected static MainPage menu;
    protected static LoginPage loginPage;
    protected static UsersAddMoneyPage usersAddMoneyPage;
    protected static UsersReadAllPage usersReadAllPage;
    protected static UsersReadUserWithCarsPage usersReadUserWithCarsPage;
    protected static CarsReadAll carsReadAll;
    protected static CarsCreateNew carsCreateNew;
    protected static CreateNewUserPage createNewUserPage;
    protected static BuySellCarPage buySellCarPage;
    protected static SettleEvictHousesPage settleEvictHousesPage;
    protected static CreateHousePage createHousePage;
    protected static FindHousePage findHousePage;
    protected static DBConnection dbConnection;
    protected static ApiConnection apiConnection;

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
        carsReadAll = new CarsReadAll();
        carsCreateNew = new CarsCreateNew();
        buySellCarPage = new BuySellCarPage();
        settleEvictHousesPage = new SettleEvictHousesPage();
        createNewUserPage = new CreateNewUserPage();
        createHousePage = new CreateHousePage();
        findHousePage = new FindHousePage();
        dbConnection = new DBConnection();
        apiConnection = new ApiConnection(API_URL);

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