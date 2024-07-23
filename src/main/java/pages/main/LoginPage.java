package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import utils.ConfigReader;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class LoginPage {
    private final static SelenideElement USERNAME_INPUT = $x("//input[@name='email']");
    private final static SelenideElement PASSWORD_INPUT = $x("//input[@name='password']");
    private final static SelenideElement LOGIN_BUTTON = $x("//button[contains(text(),\" GO\")]");
    private final static SelenideElement LOGOUT_BUTTON = $x("//button[contains(text(),\" LOGOUT\")]");
    private final static SelenideElement EMAIL_ERROR_MESSAGE = $x("//input[@name='email']/preceding-sibling::*[1]");
    private final static SelenideElement PASSWORD_ERROR_MESSAGE = $x("//input[@name='password']/preceding-sibling::*[1]");
    private final static String USERNAME = ConfigReader.get("username");
    private final static String PASSWORD = ConfigReader.get("password");

    public void fullAuthorization() {
        this.login(USERNAME, PASSWORD);
        Alert alert = switchTo().alert();
        alert.accept();
    }

    public LoginPage isPageOpen() {
        LOGIN_BUTTON.shouldBe(visible);
        return this;
    }

    public LoginPage fillEmailInput(String username) {
        USERNAME_INPUT.click();
        USERNAME_INPUT.setValue(username);
        return this;
    }

    public LoginPage fillPasswordInput(String password) {
        PASSWORD_INPUT.click();
        PASSWORD_INPUT.setValue(password);
        return this;
    }

    public LoginPage logout() {
        LOGOUT_BUTTON.click();
        return this;
    }

    public String getEmailInputValue() {
        return USERNAME_INPUT.getValue();
    }

    public String getPasswordInputValue() {
        return PASSWORD_INPUT.getValue();
    }

    public LoginPage login(String username, String password) {
        fillEmailInput(username).fillPasswordInput(password);
        LOGIN_BUTTON.click();
        return this;

    }

    public String getAlertMessage() {
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public String getEmailErrorMessage() {
        return EMAIL_ERROR_MESSAGE.getText();
    }

    public String getPasswordErrorMessage() {
        return PASSWORD_ERROR_MESSAGE.getText();
    }
}
