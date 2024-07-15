package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class LoginPage {
    private final SelenideElement usernameInput = $x("//input[@name='email']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement loginButton = $x("//button[contains(text(),\" GO\")]");
    private final SelenideElement logoutButton = $x("//button[contains(text(),\" LOGOUT\")]");
    private final SelenideElement emailErrorMessage = $x("//input[@name='email']/preceding-sibling::*[1]");
    private final SelenideElement passwordErrorMessage = $x("//input[@name='password']/preceding-sibling::*[1]");


    public LoginPage isPageOpen() {
        loginButton.shouldBe(visible);
        return this;
    }

    public LoginPage fillEmailInput(String username) {
        usernameInput.click();
        usernameInput.setValue(username);
        return this;
    }

    public LoginPage fillPasswordInput(String password) {
        passwordInput.click();
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage logout() {
        logoutButton.click();
        return this;
    }

    public String getEmailInputValue() {
        return usernameInput.getValue();
    }

    public String getPasswordInputValue() {
        return passwordInput.getValue();
    }

    public LoginPage login(String username, String password) {
        fillEmailInput(username).fillPasswordInput(password).loginButton.click();
        return this;

    }

    public String getAlertMessage() {
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }


}

