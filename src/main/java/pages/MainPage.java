package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final static SelenideElement USERS_LINK = $x("//a[text()='Users']");
    private final static SelenideElement HOUSES_LINK = $x("//a[text()='Houses']");
    private static final SelenideElement CARS_DROPDOWN = $x("//a[text() = 'Cars']");
    private final static SelenideElement CREATE_NEW_USER_LINK = $x("//a[@href='#/create/user']");
    private static final SelenideElement CARS_READ_ALL = $x("//a[text() = 'Read all']");
    private static final SelenideElement CARS_CREATE_NEW = $x("//a[text() = 'Create new']");
    private static final SelenideElement HOUSES_READ_ALL = $x("//a[text() = 'Read all']");

    public void openCarsReadAll(){
        CARS_DROPDOWN.click();
        CARS_READ_ALL.click();
    }

    public static void openCreateNewUserPage() {
        USERS_LINK.click();
        CREATE_NEW_USER_LINK.shouldBe(visible);
        CREATE_NEW_USER_LINK.click();
    }

    public void openCarsCreateNew() {
        CARS_DROPDOWN.shouldBe(visible).click();
        CARS_CREATE_NEW.shouldBe(visible).click();
    }

    public void openHousesReadAll(){
        HOUSES_LINK.shouldBe(visible).click();
        HOUSES_READ_ALL.shouldBe(visible).click();
    }
}
