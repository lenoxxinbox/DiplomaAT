package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class BuySellCarPage {
    private final SelenideElement USER_ID = $(id("id_send"));
    private final SelenideElement CAR_ID = $(id("car_send"));
    private final SelenideElement USERS_MENU = $(xpath("//a[text()='Users']"));
    private final SelenideElement BUY_SELL_CAR_MENU = $(xpath("//a[text()='Buy or sell car']"));
    private final SelenideElement BUY_RADIOBUTTON = $(xpath("//input[@value='buyCar']"));
    private final SelenideElement SELL_RADIOBUTTON = $(xpath("//input[@value='sellCar']"));
    private final SelenideElement CLICK_PUSH = $(xpath("//button[@class='tableButton btn btn-primary']"));
    private final SelenideElement RESULT_STATUS = $(xpath("//button[@class='status btn btn-secondary']"));

    public BuySellCarPage goToBuySellCarMenu () {
        USERS_MENU.click();
        BUY_SELL_CAR_MENU.click();
        return this;
    }

    public BuySellCarPage userIDInput(String id) {
        USER_ID.click();
        USER_ID.sendKeys(id);
        return this;
    }

    public BuySellCarPage carIDInput(String id) {
        CAR_ID.click();
        CAR_ID.sendKeys(id);
        return this;
    }

    public BuySellCarPage buy () {
        BUY_RADIOBUTTON.click();
        return this;
    }

    public BuySellCarPage sell () {
        SELL_RADIOBUTTON.click();
        return this;
    }

    public BuySellCarPage push () {
        CLICK_PUSH.shouldBe(visible);
        CLICK_PUSH.click();
        return this;
    }

    public String getResultMessage() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return RESULT_STATUS.shouldBe(Condition.visible).getText();
    }
}