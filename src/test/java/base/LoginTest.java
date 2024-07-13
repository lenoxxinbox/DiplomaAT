package base;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    String password = "user";
    String username = "user@pflb.ru";

    @Test
    @DisplayName("Авторизация с корректными данными")
    @Owner("Julia Sinkova")
    public void loginWithValidCredentials() {
        String successMessage = "Successful authorization";
        String alertMessage = loginPage.isPageOpen().login(username, password).getAlertMessage();
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
        String alertMessage = loginPage.isPageOpen().login(incorrectEmail, password).getAlertMessage();
        Assertions.assertEquals(alertMessage, errorMessage);
    }

    @Test
    @DisplayName("Авторизация с некорректным паролем")
    @Owner("Julia Sinkova")
    public void loginWithIncorrectPassword() {
        String incorrectPassword = "test";
        String errorMessage = "Bad request";
        String alertMessage = loginPage.isPageOpen().login(username, incorrectPassword).getAlertMessage();
        Assertions.assertEquals(alertMessage, errorMessage);
    }

    @Test
    @DisplayName("Авторизация с невалидным email")
    @Owner("Julia Sinkova")
    public void loginWithInvalidEmail() {
        String invalidEmail = "test";
        String targetAlertMessage = "Incorrect input data";
        String targetEmailErrorMessage = "incorrect Email";
        String alertMessage = loginPage.isPageOpen().login(invalidEmail, password).getAlertMessage();
        String emailErrorMessage = loginPage.getEmailErrorMessage();
        Assertions.assertAll(
                () -> Assertions.assertEquals(alertMessage, targetAlertMessage),
                () -> Assertions.assertEquals(emailErrorMessage, targetEmailErrorMessage)
        );

    }

    @Test
    @DisplayName("Проверка работы кнопки Logout")
    @Owner("Julia Sinkova")
    public void logoutButtonTest() {
        String invalidEmail = "test";
        String emailInputValue = loginPage.isPageOpen()
                .fillEmailInput(invalidEmail)
                .fillPasswordInput(password)
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
