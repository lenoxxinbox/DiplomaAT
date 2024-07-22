package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.xpath;

public class MainPage {

    private final static SelenideElement USERS_LINK = $x("//a[text()='Users']");
    private final static SelenideElement CREATE_NEW_USER_LINK = $x("//a[@href='#/create/user']");
    private final static SelenideElement HOUSES_MENU = $x("//a[contains(text(), 'Houses')]");
    private final static SelenideElement HOUSES_MENU_CREATE_NEW = $x("//a[contains(text(), 'Create new')]");
    private static final SelenideElement CARS_DROPDOWN = $x("//a[text() = 'Cars']");
    private static final SelenideElement CARS_READ_ALL = $x("//a[text() = 'Read all']");
    private static final SelenideElement CARS_CREATE_NEW = $x("//a[text() = 'Create new']");
    private static final SelenideElement USERS_DROPDOWN = $x("//a[text() = 'Users']");
    private static final SelenideElement USERS_READ_USER_WITH_CARS = $x("//a[text() = 'Read user with cars']");
    private static final SelenideElement USERS_ADD_MONEY = $x("//a[text() = 'Add money']");
    private final SelenideElement BUY_SELL_CAR_MENU = $(xpath("//a[text()='Buy or sell car']"));
    private final SelenideElement SETTLE_EVICT_HOUSES_MENU = $(xpath("//a[text()='Settle to house']"));

    public void openCarsReadAll(){
        CARS_DROPDOWN.click();
        CARS_READ_ALL.click();
    }

    public void openCreateNewUserPage() {
        USERS_LINK.click();
        CREATE_NEW_USER_LINK.shouldBe(visible);
        CREATE_NEW_USER_LINK.click();
    }
    public static void goCreateHousePage() {
        HOUSES_MENU.click();
        HOUSES_MENU_CREATE_NEW.click();
    }

    public void openCarsCreateNew() {
        CARS_DROPDOWN.shouldBe(visible).click();
        CARS_CREATE_NEW.shouldBe(visible).click();
    }

    public void openUsersReadUserWithCars() {
        USERS_DROPDOWN.shouldBe(visible).click();
        USERS_READ_USER_WITH_CARS.shouldBe(visible).click();
    }

    public void openUsersAddMoney() {
        USERS_DROPDOWN.shouldBe(visible).click();
        USERS_ADD_MONEY.shouldBe(visible).click();
    }

    public void goToBuySellCarMenu() {
        USERS_LINK.click();
        BUY_SELL_CAR_MENU.click();
    }

    public SettleEvictHousesPage goToSettleEvictHousesMenu() {
        USERS_LINK.click();
        SETTLE_EVICT_HOUSES_MENU.click();
        return null;
    }
}
