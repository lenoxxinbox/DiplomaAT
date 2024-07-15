package base;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    private final static String USERNAME = ConfigReader.get("username");
    private final static String PASSWORD = ConfigReader.get("password");

    @Test
    @DisplayName("Авторизация с корректными данными")
    @Owner("Julia Sinkova")
    public void loginWithValidCredentials() {
        String successMessage = "Successful authorization";
        String alertMessage = loginPage.isPageOpen().login(USERNAME, PASSWORD).getAlertMessage();
        Assertions.assertEquals(alertMessage, successMessage);
    }

    @Test
    @DisplayName("Авторизация с пустыми полями")
    @Owner("Julia Sinkova")
    public void loginWithEmptyFields() {
        String targetAlertMessage = "Incorrect input data";
        String targetEmailErrorMessage = "email cannot be empty";
        String targetPasswordErrorMessage = "password cannot be empty";
        String alertMessage = loginPage.isPageOpen().login("", "").getAlertMessage();
        String emailErrorMessage = loginPage.getEmailErrorMessage();
        String passwordErrorMessage = loginPage.getPasswordErrorMessage();
        Assertions.assertEquals(alertMessage, targetAlertMessage);
        Assertions.assertAll(
                () -> Assertions.assertEquals(alertMessage, targetAlertMessage),
                () -> Assertions.assertEquals(emailErrorMessage, targetEmailErrorMessage),
                () -> Assertions.assertEquals(passwordErrorMessage, targetPasswordErrorMessage)
        );
    }

    @Test
    @DisplayName("Авторизация с некорректным email")
    @Owner("Julia Sinkova")
    public void loginWithIncorrectEmail() {
        String incorrectEmail = "test@mail.com";
        String errorMessage = "Bad request";
        String alertMessage = loginPage.isPageOpen().login(incorrectEmail, PASSWORD).getAlertMessage();
        Assertions.assertEquals(alertMessage, errorMessage);
    }

    @Test
    @DisplayName("Авторизация с некорректным паролем")
    @Owner("Julia Sinkova")
    public void loginWithIncorrectPassword() {
        String incorrectPassword = "test";
        String errorMessage = "Bad request";
        String alertMessage = loginPage.isPageOpen().login(USERNAME, incorrectPassword).getAlertMessage();
        Assertions.assertEquals(alertMessage, errorMessage);
    }

    @Test
    @DisplayName("Авторизация с невалидным email")
    @Owner("Julia Sinkova")
    public void loginWithInvalidEmail() {
        String invalidEmail = "test";
        String targetAlertMessage = "Incorrect input data";
        String targetEmailErrorMessage = "incorrect Email";
        String alertMessage = loginPage.isPageOpen().login(invalidEmail, PASSWORD).getAlertMessage();
        String emailErrorMessage = loginPage.getEmailErrorMessage();
        Assertions.assertAll(
                () -> Assertions.assertEquals(alertMessage, targetAlertMessage),
                () -> Assertions.assertEquals(emailErrorMessage, targetEmailErrorMessage)
        );

    }

    @Test
    @DisplayName("Авторизация с невалидным паролем")
    @Owner("Julia Sinkova")
    public void loginWithInvalidPassword() {
        String invalidPassword = "1";
        String targetAlertMessage = "Incorrect input data";
        String targetPasswordErrorMessage = "password length must be more than 3 symbols and less than 8 symbols";
        String alertMessage = loginPage.isPageOpen().login(USERNAME, invalidPassword).getAlertMessage();
        String passwordErrorMessage = loginPage.getPasswordErrorMessage();
        Assertions.assertAll(
                () -> Assertions.assertEquals(alertMessage, targetAlertMessage),
                () -> Assertions.assertEquals(passwordErrorMessage, targetPasswordErrorMessage)
        );

    }

    @Test
    @DisplayName("Проверка работы кнопки Logout")
    @Owner("Julia Sinkova")
    public void logoutButtonTest() {
        String invalidEmail = "test";
        String emailInputValue = loginPage.isPageOpen()
                .fillEmailInput(invalidEmail)
                .fillPasswordInput(PASSWORD)
                .logout()
                .isPageOpen()
                .getEmailInputValue();
        String passwordInputValue = loginPage.getPasswordInputValue();
        Assertions.assertAll(
                () -> Assertions.assertEquals(emailInputValue, ""),
                () -> Assertions.assertEquals(passwordInputValue, "")
        );

    }


}
