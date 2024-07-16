package pages.Cars;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

public class CarsCreateNew {
    private static final SelenideElement ENGINE_TYPE_FOLD = $(id("car_engine_type_send"));
    private static final SelenideElement MARK_FOLD = $(id("car_mark_send"));
    private static final SelenideElement MODEL_FOLD = $(id("car_model_send"));
    private static final SelenideElement PRICE_FOLD = $(id("car_price_send"));
    private static final SelenideElement PUSH_TO_API_BUTTON = $x("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final SelenideElement STATUS_FOLD = $x("//*[@id=\"root\"]/div/section/div/div/button[2]");
    private static final SelenideElement NEW_ID_FOLD = $x("//*[@id=\"root\"]/div/section/div/div/button[3]");

    public CarsCreateNew isOpenPage() {
        try {
            PUSH_TO_API_BUTTON.shouldBe(Condition.visible);
        } catch (AssertionError e) {
            throw new IllegalStateException("Page is not open or PUSH button not found", e);
        }
        return this;
    }

    public CarsCreateNew createNewCar(String engineType, String mark, String model, String price){
        enterEngineType(engineType);
        enterMark(mark);
        enterModel(model);
        enterPrice(price);
        clickPushButton();
        waitForStatusChange();
        return this;
    }

    public CarsCreateNew enterEngineType(String engineType) {
        ENGINE_TYPE_FOLD.sendKeys(engineType);
        return this;
    }

    public CarsCreateNew enterMark(String mark) {
        MARK_FOLD.sendKeys(mark);
        return this;
    }

    public CarsCreateNew enterModel(String model) {
        MODEL_FOLD.sendKeys(model);
        return this;
    }

    public CarsCreateNew enterPrice(String price) {
        PRICE_FOLD.sendKeys(price);
        return this;
    }

    public CarsCreateNew clickPushButton() {
        PUSH_TO_API_BUTTON.shouldBe(Condition.visible).click();
        STATUS_FOLD.shouldBe(Condition.visible);
        return this;
    }

    public String getStatusText(){
        String status = STATUS_FOLD.shouldBe(visible).getText();
        return status;
    }

    public String getNewId() {
        String newIDText = NEW_ID_FOLD.shouldBe(Condition.visible).getText();
        String newID = newIDText.split(": ")[1].trim();
        return newID;

    }

    public CarsCreateNew waitForStatusChange() {
        STATUS_FOLD.shouldNotHave(text("Status: not pushed"), Duration.ofSeconds(10));
        return this;
    }
}
