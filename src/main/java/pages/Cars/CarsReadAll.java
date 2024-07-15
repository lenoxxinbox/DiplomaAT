package pages.Cars;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CarsReadAll {

    private static final SelenideElement RELOAD_BUTTON = $x("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final SelenideElement SORT_BY_ID = $x("//*[@id=\"root\"]/div/section/div/div/button[3]");
    private static final SelenideElement SORT_BY_ENGINE_TYPE = $x("//*[@id=\"root\"]/div/section/div/div/button[4]");
    private static final SelenideElement SORT_BY_MARK = $x("//*[@id=\"root\"]/div/section/div/div/button[5]");
    private static final SelenideElement SORT_BY_MODEL = $x("//*[@id=\"root\"]/div/section/div/div/button[6]");
    private static final SelenideElement SORT_BY_PRICE = $x("//*[@id=\"root\"]/div/section/div/div/button[7]");

    WebDriver driver = WebDriverRunner.getWebDriver();
    Actions actions = new Actions(driver);

    public CarsReadAll clickSortById() {
        SelenideElement element = SORT_BY_ID.shouldBe(visible);
        actions.click(element).perform();
        return this;
    }

    public CarsReadAll clickSortByEngineType() {
        SelenideElement element = SORT_BY_ENGINE_TYPE.shouldBe(visible);
        actions.click(element).perform();
        return this;
    }

    public CarsReadAll clickSortByMark() {
        SelenideElement element = SORT_BY_MARK.shouldBe(visible);
        actions.click(element).perform();
        return this;
    }

    public CarsReadAll clickSortByModel() {
        SelenideElement element = SORT_BY_MODEL.shouldBe(visible);
        actions.click(element).perform();
        return this;
    }

    public CarsReadAll clickSortByPrice() {
        SelenideElement element = SORT_BY_PRICE.shouldBe(visible);
        actions.click(element).perform();
        return this;
    }

    public CarsReadAll clickReloadButton() {
        SelenideElement element = RELOAD_BUTTON.shouldBe(visible);
        actions.click(element).perform();
        return this;
    }

    public CarsReadAll getListForId(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[1]")
                .shouldHave(sizeGreaterThan(0));
        collectList(true, ascending, list);
        return this;
    }

    public CarsReadAll getListForEngineType(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[2]")
                .shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    public CarsReadAll getListForMark(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[3]")
                .shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    public CarsReadAll getListForModel(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[3]")
                .shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    public CarsReadAll getListForPrice(boolean ascending){
        ElementsCollection list = $$x("//tbody//tr//td[3]")
                .shouldHave(sizeGreaterThan(0));
        collectList(true, ascending, list);
        return this;
    }

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
        for (T num : valueList) {
            System.out.println(num);
        }
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

    public String getFirstId(){
        String firstId = $(By.xpath("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[1]")).getText();
        return firstId;
    }
}
