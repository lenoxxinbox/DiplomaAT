package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class SettleEvictHousesPage {
    private final SelenideElement userID = $(id("id_send"));
    private final SelenideElement houseID = $(id("house_send"));
    private final SelenideElement usersMenu = $(xpath("//a[text()='Users']"));
    private final SelenideElement settleEvictHousesMenu = $(xpath("//a[text()='Settle to house']"));
    private final SelenideElement settleRadiobutton = $(xpath("//input[@value='settle']"));
    private final SelenideElement evictRadiobutton = $(xpath("//input[@value='evict']"));
    private final SelenideElement clickPush = $(xpath("//button[@class='tableButton btn btn-primary']"));
    private final SelenideElement resultStatus = $(xpath("//button[@class='status btn btn-secondary']"));

    public SettleEvictHousesPage goToSettleEvictHousesMenu () {
        usersMenu.click();
        settleEvictHousesMenu.click();
        return this;
    }

    public SettleEvictHousesPage userIDInput(String id) {
        userID.click();
        userID.sendKeys(id);
        return this;
    }

    public SettleEvictHousesPage houseIDInput(String id) {
        houseID.click();
        houseID.sendKeys(id);
        return this;
    }

    public SettleEvictHousesPage settle() {
        settleRadiobutton.click();
        return this;
    }

    public SettleEvictHousesPage evict() {
        evictRadiobutton.click();
        return this;
    }

    public SettleEvictHousesPage push() {
        clickPush.shouldBe(visible);
        clickPush.click();
        return this;
    }

    public String getResultMessage() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultStatus.shouldBe(Condition.visible).getText();
    }
}
