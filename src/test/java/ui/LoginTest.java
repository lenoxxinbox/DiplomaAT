package ui;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Проверка авторизации")
public class LoginTest extends BaseTest {

    private final static String USERNAME = ConfigReader.get("username");
    private final static String PASSWORD = ConfigReader.get("password");
    private final static String SUCCESS_MESSAGE = "Successful authorization";
    private final static String BAD_REQUEST_MESSAGE = "Bad request";
    private final static String EMPTY_EMAIL_ERROR_MESSAGE = "email cannot be empty";
    private final static String EMPTY_PASSWORD_ERROR_MESSAGE = "password cannot be empty";
    private final static String INCORRECT_INPUT_MESSAGE = "Incorrect input data";
    private final static String INCORRECT_EMAIL = "test@mail.com";
    private final static String INCORRECT_PASSWORD = "test";
    private final static String INVALID_EMAIL = "test12412*?";
    private final static String INVALID_EMAIL_ERROR_MESSAGE = "incorrect Email";
    private final static String INVALID_PASSWORD = "1";
    private final static String INVALID_PASSWORD_ERROR_MESSAGE = "password length must be more than 3 symbols and less than 8 symbols";

    @Test
    @DisplayName("Проверка авторизации с корректными данными")
    @Owner("Julia Sinkova")
    public void loginWithValidCredentials() {
        String alertMessage = loginPage
                .isPageOpen()
                .login(USERNAME, PASSWORD)
                .getAlertMessage();
        assertEquals(SUCCESS_MESSAGE, alertMessage, "Сообщение об аутентификации должно быть корректным");
    }

    @Test
    @DisplayName("Проверка авторизации с пустыми полями")
    @Owner("Julia Sinkova")
    public void loginWithEmptyFields() {
        String alertMessage = loginPage
                .isPageOpen()
                .login("", "")
                .getAlertMessage();
        String emailErrorMessage = loginPage.getEmailErrorMessage();
        String passwordErrorMessage = loginPage.getPasswordErrorMessage();

        assertAll(
                () -> assertEquals(INCORRECT_INPUT_MESSAGE, alertMessage, "Сообщение об ошибке должно быть корректным"),
                () -> assertEquals(EMPTY_EMAIL_ERROR_MESSAGE, emailErrorMessage, "Сообщение рядом с полем email должно быть корректным"),
                () -> assertEquals(EMPTY_PASSWORD_ERROR_MESSAGE, passwordErrorMessage, "Сообщение рядом с полем password должно быть корректным")
        );
    }

    @Test
    @DisplayName("Проверка авторизации с некорректным email")
    @Owner("Julia Sinkova")
    public void loginWithIncorrectEmail() {
        String alertMessage = loginPage
                .isPageOpen()
                .login(INCORRECT_EMAIL, PASSWORD)
                .getAlertMessage();
        assertEquals(BAD_REQUEST_MESSAGE, alertMessage, "Сообщение об ошибке должно быть корректным");
    }

    @Test
    @DisplayName("Проверка авторизации с некорректным паролем")
    @Owner("Julia Sinkova")
    public void loginWithIncorrectPassword() {
        String alertMessage = loginPage
                .isPageOpen()
                .login(USERNAME, INCORRECT_PASSWORD)
                .getAlertMessage();
        assertEquals(BAD_REQUEST_MESSAGE, alertMessage, "Сообщение об ошибке должно быть корректным");
    }

    @Test
    @DisplayName("Проверка авторизации с невалидным email")
    @Owner("Julia Sinkova")
    public void loginWithInvalidEmail() {
        String alertMessage = loginPage
                .isPageOpen()
                .login(INVALID_EMAIL, PASSWORD)
                .getAlertMessage();
        String emailErrorMessage = loginPage.getEmailErrorMessage();
        assertAll(
                () -> assertEquals(INCORRECT_INPUT_MESSAGE, alertMessage, "Сообщение об ошибке должно быть корректным"),
                () -> assertEquals(INVALID_EMAIL_ERROR_MESSAGE, emailErrorMessage, "Сообщение рядом с полем email должно быть корректным")
        );
    }

    @Test
    @DisplayName("Проверка авторизации с невалидным паролем")
    @Owner("Julia Sinkova")
    public void loginWithInvalidPassword() {
        String alertMessage = loginPage
                .isPageOpen()
                .login(USERNAME, INVALID_PASSWORD)
                .getAlertMessage();
        String passwordErrorMessage = loginPage.getPasswordErrorMessage();
        assertAll(
                () -> assertEquals(INCORRECT_INPUT_MESSAGE, alertMessage, "Сообщение об ошибке должно быть корректным"),
                () -> assertEquals(INVALID_PASSWORD_ERROR_MESSAGE, passwordErrorMessage, "Сообщение рядом с полем password должно быть корректным")
        );
    }

    @Test
    @DisplayName("Проверка работы кнопки Logout")
    @Owner("Julia Sinkova")
    public void logoutButtonTest() {
        String emailInputValue = loginPage.isPageOpen()
                .fillEmailInput(INVALID_EMAIL)
                .fillPasswordInput(PASSWORD)
                .logout()
                .isPageOpen()
                .getEmailInputValue();
        String passwordInputValue = loginPage.getPasswordInputValue();
        assertAll(
                () -> assertEquals("", emailInputValue, "Поле email должно быть пустым"),
                () -> assertEquals("", passwordInputValue, "Поле password должно быть пустым")
        );
    }
}
