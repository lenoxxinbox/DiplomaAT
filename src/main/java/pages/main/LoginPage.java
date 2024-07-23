package pages.main;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Авторизация")
    public void fullAuthorization() {
        this.login(USERNAME, PASSWORD);
        Alert alert = switchTo().alert();
        alert.accept();
    }

    @Step("Проверка открытия страницы")
    public LoginPage isPageOpen() {
        LOGIN_BUTTON.shouldBe(visible);
        return this;
    }

    @Step("Заполнение поля email")
    public LoginPage fillEmailInput(String username) {
        USERNAME_INPUT.click();
        USERNAME_INPUT.setValue(username);
        return this;
    }

    @Step("Заполнение поля password")
    public LoginPage fillPasswordInput(String password) {
        PASSWORD_INPUT.click();
        PASSWORD_INPUT.setValue(password);
        return this;
    }

    @Step("Нажатие на кнопку Logout")
    public LoginPage logout() {
        LOGOUT_BUTTON.click();
        return this;
    }

    @Step("Получение содержимого поля email")
    public String getEmailInputValue() {
        return USERNAME_INPUT.getValue();
    }

    @Step("Получение содержимого поля password")
    public String getPasswordInputValue() {
        return PASSWORD_INPUT.getValue();
    }

    @Step("Выполнение логина")
    public LoginPage login(String username, String password) {
        fillEmailInput(username).fillPasswordInput(password);
        LOGIN_BUTTON.click();
        return this;

    }

    @Step("Получение текста alert сообщения")
    public String getAlertMessage() {
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    @Step("Получение текста ошибки у поля email")
    public String getEmailErrorMessage() {
        return EMAIL_ERROR_MESSAGE.getText();
    }

    @Step("Получение текста ошибки у поля password")
    public String getPasswordErrorMessage() {
        return PASSWORD_ERROR_MESSAGE.getText();
    }
}
