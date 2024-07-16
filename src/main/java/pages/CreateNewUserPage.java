package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreateNewUserPage {
    private final static SelenideElement FIRST_NAME_INPUT = $("#first_name_send");
    private final static SelenideElement SECOND_NAME_INPUT = $("#last_name_send");
    private final static SelenideElement AGE_INPUT = $("#age_send");
    private final static SelenideElement MONEY_INPUT = $("#money_send");
    private final static SelenideElement SEX_MALE_INPUT = $x("//input[@id='sex_send' and @value='MALE']");
    private final static SelenideElement SEX_FEMALE_INPUT = $x("//input[@id='sex_send' and @value='FEMALE']");
    private final static SelenideElement PUSH_BUTTON = $x("//button[contains(text(), \"PUSH\")]");
    private final static SelenideElement STATUS_BUTTON = $x("//button[contains(text(), \"Status\")]");
    private final static SelenideElement NEW_USER_BUTTON = $x("//button[contains(@class,'newId')]");
    WebDriver driver = WebDriverRunner.getWebDriver();
    Actions actions = new Actions(driver);

    public CreateNewUserPage isPageOpen() {
        PUSH_BUTTON.shouldBe(visible);
        return this;
    }


    public CreateNewUserPage createUser(String firstName, String secondName, int age, int money, boolean isMale) {

        if (isMale) {
            actions.click(SEX_MALE_INPUT).perform();
            SEX_MALE_INPUT.shouldBe(selected);
        } else {
            actions.click(SEX_FEMALE_INPUT).perform();
            SEX_FEMALE_INPUT.shouldBe(selected);
        }
        FIRST_NAME_INPUT.sendKeys(firstName);
        SECOND_NAME_INPUT.sendKeys(secondName);
        MONEY_INPUT.sendKeys(String.valueOf(money));
        AGE_INPUT.sendKeys(String.valueOf(age));
        PUSH_BUTTON.click();
        return this;
    }

    public CreateNewUserPage clickPushButton() {
        PUSH_BUTTON.click();
        return this;
    }

    public String getStatusMessage(String expectedMessage) {

        STATUS_BUTTON.shouldHave(text(expectedMessage));
        return STATUS_BUTTON.getText();
    }

    public String getNewUserId() {
        NEW_USER_BUTTON.shouldBe(visible);
        String text = NEW_USER_BUTTON.getText();
        return text.replaceAll("^.*?(\\d+)$", "$1");

    }

}
