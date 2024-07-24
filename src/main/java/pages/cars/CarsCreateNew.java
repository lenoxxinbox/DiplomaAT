package pages.cars;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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
    private static final String BASIC_STATUS = "Status: not pushed";

    @Step("Проверка открытия страницы")
    public CarsCreateNew isOpenPage() {
        try {
            PUSH_TO_API_BUTTON.shouldBe(Condition.visible);
        } catch (AssertionError e) {
            throw new IllegalStateException("Page is not open or PUSH button not found", e);
        }
        return this;
    }

    @Step("Создание автомобиля")
    public CarsCreateNew createNewCar(String engineType, String mark, String model, String price) {
        enterEngineType(engineType);
        enterMark(mark);
        enterModel(model);
        enterPrice(price);
        clickPushButton();
        waitForStatusChange();
        return this;
    }

    @Step("Ввод типа двигателя")
    public CarsCreateNew enterEngineType(String engineType) {
        ENGINE_TYPE_FOLD.sendKeys(engineType);
        return this;
    }

    @Step("Ввод марки автомобиля")
    public CarsCreateNew enterMark(String mark) {
        MARK_FOLD.sendKeys(mark);
        return this;
    }

    @Step("Ввод типа модели")
    public CarsCreateNew enterModel(String model) {
        MODEL_FOLD.sendKeys(model);
        return this;
    }

    @Step("Ввод цены")
    public CarsCreateNew enterPrice(String price) {
        if (price != null) {
            PRICE_FOLD.sendKeys(price.toString());
        }
        return this;
    }

    @Step("Нажатие на кнопку Push to Api")
    public CarsCreateNew clickPushButton() {
        PUSH_TO_API_BUTTON
                .shouldBe(Condition.visible)
                .click();
        STATUS_FOLD.shouldBe(Condition.visible);
        return this;
    }

    @Step("Получение текств в поле статуса")
    public String getStatusText() {
        String status = STATUS_FOLD
                .shouldBe(visible)
                .getText();
        return status;
    }

    @Step("Получение ID нового автомобиля")
    public String getNewId() {
        String newIDText = NEW_ID_FOLD
                .shouldBe(Condition.visible)
                .getText();
        String newID = newIDText.split(": ")[1].trim();
        return newID;

    }

    @Step("Ожидание изменения поля статус")
    public CarsCreateNew waitForStatusChange() {
        STATUS_FOLD.shouldNotHave(text(BASIC_STATUS), Duration.ofSeconds(10));
        return this;
    }
}
