package pages.cars;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CarsReadAll {

    private static final SelenideElement RELOAD_BUTTON = $x("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final SelenideElement SORT_BY_ID = $x("//*[@id=\"root\"]/div/section/div/div/button[3]");
    private static final SelenideElement SORT_BY_ENGINE_TYPE = $x("//*[@id=\"root\"]/div/section/div/div/button[4]");
    private static final SelenideElement SORT_BY_MARK = $x("//*[@id=\"root\"]/div/section/div/div/button[5]");
    private static final SelenideElement SORT_BY_MODEL = $x("//*[@id=\"root\"]/div/section/div/div/button[6]");
    private static final SelenideElement SORT_BY_PRICE = $x("//*[@id=\"root\"]/div/section/div/div/button[7]");
    private static final SelenideElement TABLE_BODY = $x("//table/tbody");

    WebDriver driver = WebDriverRunner.getWebDriver();
    Actions actions = new Actions(driver);

    @Step("Клик на кнопку сортировку по ID")
    public CarsReadAll clickSortById() {
        TABLE_BODY.shouldBe(visible);
        SelenideElement element = SORT_BY_ID.shouldBe(visible);
        actions
                .click(element)
                .perform();
        return this;
    }

    @Step("Клик на кнопку сортировку по типу двигателя")
    public CarsReadAll clickSortByEngineType() {
        TABLE_BODY.shouldBe(visible);
        SelenideElement element = SORT_BY_ENGINE_TYPE.shouldBe(visible);
        actions
                .click(element)
                .perform();
        return this;
    }

    @Step("Клик на кнопку сортировку по марке")
    public CarsReadAll clickSortByMark() {
        TABLE_BODY.shouldBe(visible);
        SelenideElement element = SORT_BY_MARK.shouldBe(visible);
        actions
                .click(element)
                .perform();
        return this;
    }

    @Step("Клик на кнопку сортировку по модели")
    public CarsReadAll clickSortByModel() {
        TABLE_BODY.shouldBe(visible);
        SelenideElement element = SORT_BY_MODEL.shouldBe(visible);
        actions
                .click(element)
                .perform();
        return this;
    }

    @Step("Клик на кнопку сортировку по цене")
    public CarsReadAll clickSortByPrice() {
        TABLE_BODY.shouldBe(visible);
        SelenideElement element = SORT_BY_PRICE.shouldBe(visible);
        actions
                .click(element)
                .perform();
        return this;
    }

    @Step("Клик на кнопку Reload")
    public CarsReadAll clickReloadButton() {
        TABLE_BODY.shouldBe(visible);
        SelenideElement element = RELOAD_BUTTON.shouldBe(visible);
        actions
                .click(element)
                .perform();
        return this;
    }

    @Step("Получение List по полю ID")
    public CarsReadAll getListForId(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[1]")
                .shouldHave(sizeGreaterThan(0));
        collectList(true, ascending, list);
        return this;
    }

    @Step("Получение List по полю типу двигателя")
    public CarsReadAll getListForEngineType(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[2]")
                .shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    @Step("Получение List по полю марке")
    public CarsReadAll getListForMark(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[3]")
                .shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    @Step("Получение List по полю модели")
    public CarsReadAll getListForModel(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[4]")
                .shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    @Step("Получение List по полю цене")
    public CarsReadAll getListForPrice(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[5]")
                .shouldHave(sizeGreaterThan(0));
        collectList(true, ascending, list);
        return this;
    }

    @Step("Создание и сортировка коллекции по полученному List")
    private void collectList(boolean isNumeric, boolean ascending, ElementsCollection list) {
        if (isNumeric) {
            List<Long> sortedList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String text = list.get(i).getText();
                try {
                    long number = Long.parseLong(text);
                    sortedList.add(number);
                } catch (NumberFormatException e) {
                    System.err.println();
                }
            }
            checkSortingOrder(sortedList, ascending);
        } else {
            List<String> sortedList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                sortedList.add(list.get(i).getText());
            }
            checkSortingOrder(sortedList, ascending);
        }
    }


    private <T extends Comparable<? super T>> void checkSortingOrder(List<T> valueList, boolean ascending) {
        if (valueList.isEmpty()) {
            throw new AssertionError("No valid data found for sorting.");
        }

        if (ascending) {
            if (!isSortedUp(valueList)) {
                throw new AssertionError("Data is not sorted in ascending order");
            }
        } else {
            if (!isSortedDesc(valueList)) {
                throw new AssertionError("Data is not sorted in descending order");
            }
        }
    }

    private <T extends Comparable<? super T>> boolean isSortedUp(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private <T extends Comparable<? super T>> boolean isSortedDesc(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public CarsReadAll assertSortButtonVisible(String buttonText) {
        $$(By.xpath("//button[text() = '" + buttonText + "']")).filter(visible).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Получение первого ID в таблице")
    public String getFirstId(){
        String firstId = $(By.xpath("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[1]")).getText();
        TABLE_BODY.shouldBe(visible);
        return firstId;
    }

    public boolean isIdCorrect(String expectedId) {
        TABLE_BODY.shouldBe(visible);
        SelenideElement firstId = $(By.xpath("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[1]"));
        firstId.shouldHave(text(expectedId));
        return expectedId.equals(firstId.getText());
    }
}
