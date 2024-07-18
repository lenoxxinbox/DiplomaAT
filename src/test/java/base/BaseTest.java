package base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.*;
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
    protected static UsersAddMoneyPage usersAddMoneyPage;
    protected static UsersReadAllPage usersReadAllPage;
    protected static UsersReadUserWithCarsPage usersReadUserWithCarsPage;
    protected static CarsReadAll carsReadAll;
    protected static CarsCreateNew carsCreateNew;

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
    }

    @AfterEach
    public void tearDownAfterClass() {
        closeWebDriver();
    }
}