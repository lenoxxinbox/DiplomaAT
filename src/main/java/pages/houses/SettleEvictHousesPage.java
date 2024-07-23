package pages.houses;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class SettleEvictHousesPage {
    private final SelenideElement userID = $(id("id_send"));
    private final SelenideElement houseID = $(id("house_send"));
    private final SelenideElement SETTLE_RADIOBUTTON = $(xpath("//input[@value='settle']"));
    private final SelenideElement EVICT_RADIOBUTTON = $(xpath("//input[@value='evict']"));
    private final SelenideElement CLICK_PUSH = $(xpath("//button[@class='tableButton btn btn-primary']"));
    private final SelenideElement RESULT_STATUS = $(xpath("//button[@class='status btn btn-secondary']"));
    private static final SelenideElement STATUS_FOLD = $(xpath("//*[@id=\"root\"]/div/section/div/div/button[2]"));

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
        SETTLE_RADIOBUTTON.click();
        return this;
    }

    public SettleEvictHousesPage evict() {
        EVICT_RADIOBUTTON.click();
        return this;
    }

    public SettleEvictHousesPage push() {
        CLICK_PUSH.shouldBe(visible);
        CLICK_PUSH.click();
        waitForStatusChange();
        return this;
    }

    public String getResultMessage() {
        return RESULT_STATUS.shouldBe(Condition.visible).getText();
    }

    public void waitForStatusChange() {
        STATUS_FOLD.shouldNotHave(text("Status: not pushed"), Duration.ofSeconds(10));
    }
}
