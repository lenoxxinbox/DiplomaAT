package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final static SelenideElement USERS_LINK = $x("//a[text()='Users']");
    private final static SelenideElement CREATE_NEW_USER_LINK = $x("//a[@href='#/create/user']");

    public void openCreateNewUserPage() {
        USERS_LINK.click();
        CREATE_NEW_USER_LINK.shouldBe(visible);
        CREATE_NEW_USER_LINK.click();
    }
}
