package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private static final SelenideElement USERS_DROPDOWN = $x("//a[text() = 'Users']");
    private static final SelenideElement USERS_READ_ALL = $x("//a[text() = 'Read all']");

    private static final SelenideElement USERS_READ_USER_WITH_CARS = $x("//a[text() = 'Read user with cars']");

    private static final SelenideElement USERS_ADD_MONEY = $x("//a[text() = 'Add money']");

    public void openCarsReadAll() {
        USERS_DROPDOWN.shouldBe(visible).click();
        USERS_READ_ALL.shouldBe(visible).click();
    }

    public void openUsersReadUserWithCars() {
        USERS_DROPDOWN.shouldBe(visible).click();
        USERS_READ_USER_WITH_CARS.shouldBe(visible).click();
    }

    public void openUsersAddMoney() {
        USERS_DROPDOWN.shouldBe(visible).click();
        USERS_ADD_MONEY.shouldBe(visible).click();
    }

}