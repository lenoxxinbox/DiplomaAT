package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final static SelenideElement USERS_LINK = $x("//a[text()='Users']");
    private final static SelenideElement CREATE_NEW_USER_LINK = $x("//a[@href='#/create/user']");
    private final static SelenideElement HOUSES_MENU = $x("//a[contains(text(), 'Houses')]");
    private final static SelenideElement HOUSES_MENU_CREATE_NEW = $x("//a[contains(text(), 'Create new')]");

    public static void openCreateNewUserPage() {
        USERS_LINK.click();
        CREATE_NEW_USER_LINK.shouldBe(visible);
        CREATE_NEW_USER_LINK.click();
    }
    public static void goCreateHousePage() {
        HOUSES_MENU.click();
        HOUSES_MENU_CREATE_NEW.click();
    }
}
