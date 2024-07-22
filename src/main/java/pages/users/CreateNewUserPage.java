package pages.users;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
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
        this.fillFirstName(firstName)
                .fillSecondName(secondName)
                .fillAge(age)
                .fillMoney(money)
                .selectSex(isMale)
                .clickPushButton();
        return this;
    }

    public CreateNewUserPage fillFirstName(String firstName) {
        actions.click(FIRST_NAME_INPUT).perform();
        actions.sendKeys(FIRST_NAME_INPUT, firstName);
        return this;
    }

    public CreateNewUserPage fillSecondName(String secondName) {
        actions.click(SECOND_NAME_INPUT).perform();
        actions.sendKeys(SECOND_NAME_INPUT, secondName);
        return this;
    }

    public CreateNewUserPage fillAge(int age) {
        actions.sendKeys(AGE_INPUT, String.valueOf(age));
        return this;
    }

    public CreateNewUserPage fillMoney(int money) {
        actions.sendKeys(MONEY_INPUT, String.valueOf(money));
        return this;
    }

    public CreateNewUserPage selectSex(boolean isMale) {
        if (isMale) {
            actions.click(SEX_MALE_INPUT).perform();
            SEX_MALE_INPUT.shouldBe(selected);
        } else {
            actions.click(SEX_FEMALE_INPUT).perform();
            SEX_FEMALE_INPUT.shouldBe(selected);
        }
        return this;
    }

    public CreateNewUserPage clickPushButton() {
        PUSH_BUTTON.shouldBe(visible);
        actions.click(PUSH_BUTTON).perform();
        return this;
    }

    public boolean isStatusMessageCorrect(String expectedMessage) {
        STATUS_BUTTON.shouldHave(text(expectedMessage), Duration.ofSeconds(10));
        return STATUS_BUTTON.getText().equals(expectedMessage);
    }

    public String getNewUserId() {
        NEW_USER_BUTTON.shouldBe(visible);
        String text = NEW_USER_BUTTON.getText();
        return text.replaceAll("^.*?(\\d+)$", "$1");
    }
}
