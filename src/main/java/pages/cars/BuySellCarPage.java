package pages.cars;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class BuySellCarPage {
    private final SelenideElement USER_ID = $(id("id_send"));
    private final SelenideElement CAR_ID = $(id("car_send"));
    private final SelenideElement BUY_RADIOBUTTON = $(xpath("//input[@value='buyCar']"));
    private final SelenideElement SELL_RADIOBUTTON = $(xpath("//input[@value='sellCar']"));
    private final SelenideElement CLICK_PUSH = $(xpath("//button[@class='tableButton btn btn-primary']"));
    private final SelenideElement RESULT_STATUS = $(xpath("//button[@class='status btn btn-secondary']"));
    private static final SelenideElement STATUS_FOLD = $(xpath("//*[@id=\"root\"]/div/section/div/div/button[2]"));

    @Step("Заполнение поля userID")
    public BuySellCarPage userIDInput(String id) {
        USER_ID.click();
        USER_ID.sendKeys(id);
        return this;
    }

    @Step("Заполнение поля carID")
    public BuySellCarPage carIDInput(String id) {
        CAR_ID.click();
        CAR_ID.sendKeys(id);
        return this;
    }

    @Step("Выбор покупки машины")
    public BuySellCarPage buy () {
        BUY_RADIOBUTTON.click();
        return this;
    }

    @Step("Выбор продажи машины")
    public BuySellCarPage sell () {
        SELL_RADIOBUTTON.click();
        return this;
    }

    @Step("Нажатие кнопки PUSH")
    public BuySellCarPage push () {
        CLICK_PUSH.shouldBe(visible);
        CLICK_PUSH.click();
        waitForStatusChange();
        return this;
    }

    @Step("Получение статуса")
    public String getResultMessage() {
        return RESULT_STATUS.shouldBe(Condition.visible).getText();
    }

    @Step("Ожидание статуса")
    public void waitForStatusChange() {
        STATUS_FOLD.shouldNotHave(text("Status: not pushed"), Duration.ofSeconds(10));
    }
}
