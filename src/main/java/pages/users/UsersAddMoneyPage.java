package pages.users;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.cars.CarsCreateNew;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class UsersAddMoneyPage {
    private final SelenideElement USER_ID = $(id("id_send"));
    private final SelenideElement SEND_MONEY = $(id("money_send"));
    private final SelenideElement PUSH_BUTTON = $(xpath("//button[@class='tableButton btn btn-primary']"));
    private final SelenideElement STATUS_INFO = $(xpath("//button[@class='status btn btn-secondary']"));
    private final SelenideElement USERS_MENU = $(xpath("//a[text()='Users']"));
    private final SelenideElement ADD_MONEY_MENU = $(xpath("//a[@href='#/read/users' and text()='Read all']"));
    private static final String BASIC_STATUS = "Status: not pushed";

    public UsersAddMoneyPage goToAddMoneyMenu() {
        USERS_MENU.click();
        ADD_MONEY_MENU.click();
        return this;
    }

    public UsersAddMoneyPage addMoneyToUser(String id, String money) {
        USER_ID.sendKeys(id);
        SEND_MONEY.sendKeys(money);
        PUSH_BUTTON.click();
        waitForStatusChange();
        return this;
    }

    public String statusInfo() {
        STATUS_INFO.getText();
        return STATUS_INFO.getText();
    }

    @Step("Ожидание изменения поля статус")
    public UsersAddMoneyPage waitForStatusChange() {
        STATUS_INFO.shouldNotHave(text(BASIC_STATUS), Duration.ofSeconds(10));
        return this;
    }
}
