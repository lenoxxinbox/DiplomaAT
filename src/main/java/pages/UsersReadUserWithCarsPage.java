package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
public class UsersReadUserWithCarsPage {
    private final SelenideElement USER_ID = $(id("user_input"));
    private final SelenideElement TABLE = $(xpath("//table[@class='tableUser table table-striped table-bordered table-hover']"));
    private final SelenideElement READ_BUTTON = $(xpath("//button[text()='Read']"));
    private final SelenideElement STATUS_INFO = $(xpath("//button[@class='status btn btn-secondary']"));
    private final SelenideElement USERS_MENU = $(xpath("//a[text()='Users']"));
    private final SelenideElement READ_USER_WITH_CARS_MENU = $(xpath("//a[@href='#/read/users' and text()='Read all']"));


    public UsersReadUserWithCarsPage goToReadUserWithCarsMenu () {
        USERS_MENU.click();
        READ_USER_WITH_CARS_MENU.click();
        return this;
    }

    public UsersReadUserWithCarsPage findUserWithCars (String id) {
        USER_ID.sendKeys(id);
        READ_BUTTON.click();
        $(TABLE).shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }


    public String statusInfo () {
        STATUS_INFO.getText();
        return STATUS_INFO.getText();
    }


}
