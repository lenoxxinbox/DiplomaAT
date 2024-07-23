package pages.users;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.xpath;

public class UsersReadAllPage {
    private final SelenideElement RELOAD_BUTTON = $(xpath("//button[text()='Reload']"));
    private final SelenideElement SORT_BY_ID_BUTTON = $(xpath("//button[@class='btn btn-secondary'][2]"));
    private final SelenideElement SORT_BY_FIRST_NAME_BUTTON = $(xpath("//button[@class='btn btn-secondary'][3]"));
    private final SelenideElement SORT_BY_LAST_NAME_BUTTON = $(xpath("//button[@class='btn btn-secondary'][4]"));
    private final SelenideElement SORT_BY_AGE_BUTTON = $(xpath("//button[@class='btn btn-secondary'][5]"));
    private final SelenideElement SORT_BY_SEX_BUTTON = $(xpath("//button[@class='btn btn-secondary'][6]"));
    private final SelenideElement SORT_BY_MONEY_BUTTON = $(xpath("//button[@class='btn btn-secondary'][7]"));
    private final SelenideElement USERS_MENU = $(xpath("//a[text()='Users']"));
    private final SelenideElement READ_ALL_USERS_MENU = $(xpath("//a[@href='#/read/users' and text()='Read all']"));
    private final SelenideElement TABLE_BODY = $(xpath("//table/tbody"));

    public UsersReadAllPage goToReadAllUsersMenu() {
        USERS_MENU.click();
        READ_ALL_USERS_MENU.click();
        return this;
    }

    public UsersReadAllPage reload() {
        TABLE_BODY.shouldBe(visible);
        RELOAD_BUTTON.shouldBe(visible);
        RELOAD_BUTTON.click();
        return this;
    }

    public UsersReadAllPage sortById() {
        TABLE_BODY.shouldBe(visible);
        SORT_BY_ID_BUTTON.shouldBe(visible);
        SORT_BY_ID_BUTTON.click();
        return this;
    }

    public UsersReadAllPage sortByFirstName() {
        TABLE_BODY.shouldBe(visible);
        SORT_BY_FIRST_NAME_BUTTON.shouldBe(visible);
        SORT_BY_FIRST_NAME_BUTTON.click();
        return this;
    }

    public UsersReadAllPage sortByLastName() {
        TABLE_BODY.shouldBe(visible);
        SORT_BY_LAST_NAME_BUTTON.shouldBe(visible);
        SORT_BY_LAST_NAME_BUTTON.click();
        return this;
    }

    public UsersReadAllPage sortByAge() {
        TABLE_BODY.shouldBe(visible);
        SORT_BY_AGE_BUTTON.shouldBe(visible);
        SORT_BY_AGE_BUTTON.click();
        return this;
    }

    public UsersReadAllPage sortBySex() {
        TABLE_BODY.shouldBe(visible);
        SORT_BY_SEX_BUTTON.shouldBe(visible);
        SORT_BY_SEX_BUTTON.click();
        return this;
    }

    public UsersReadAllPage sortByMoney() {
        TABLE_BODY.shouldBe(visible);
        SORT_BY_MONEY_BUTTON.shouldBe(visible);
        SORT_BY_MONEY_BUTTON.click();
        return this;
    }

    public UsersReadAllPage getListForId(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[1]").shouldHave(sizeGreaterThan(0));
        collectList(true, ascending, list);
        return this;
    }

    public UsersReadAllPage getListForFirstName(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[2]").shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    public UsersReadAllPage getListForLastName(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[3]").shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    public UsersReadAllPage getListForAge(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[3]").shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    public UsersReadAllPage getListForSex(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[3]").shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    public UsersReadAllPage getListForMoney(boolean ascending) {
        ElementsCollection list = $$x("//tbody//tr//td[3]").shouldHave(sizeGreaterThan(0));
        collectList(false, ascending, list);
        return this;
    }

    private void collectList(boolean isNumeric, boolean ascending, ElementsCollection list) {
        List<String> sortedList = new ArrayList<>();
        for (SelenideElement element : list) {
            String text = element.getText();
            if (text != null && !text.isEmpty() && !text.trim().isEmpty()) {
                sortedList.add(text);
            }
        }

        if (isNumeric) {
            try {
                List<Long> numericList = new ArrayList<>();
                for (String text : sortedList) {
                    numericList.add(Long.parseLong(text));
                }
                checkSortingOrder(numericList, ascending);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка преобразования строки в число: " + e.getMessage());
            }
        } else {
            checkSortingOrder(sortedList, ascending);
        }
    }

    private <T extends Comparable<? super T>> void checkSortingOrder(List<T> valueList, boolean ascending) {
        if (valueList.isEmpty()) {
            System.err.println("Не найдено допустимых данных для сортировки.");
            return;
        }

        if (ascending) {
            Collections.sort(valueList);
            if (isSortedUp(valueList)) {
                System.out.println("Данные успешно отсортированы по возрастанию.");
            } else {
                System.err.println("Данные не отсортированы по возрастанию.");
            }
        } else {
            Collections.sort(valueList, Collections.reverseOrder());
            if (isSortedDesc(valueList)) {
                System.out.println("Данные успешно отсортированы по убыванию.");
            } else {
                System.err.println("Данные не отсортированы по убыванию.");
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


    public UsersReadAllPage sortButtonVisible(String buttonText) {
        $$(By.xpath("//button[text() = '" + buttonText + "']"))
                .filter(visible)
                .shouldHave(sizeGreaterThan(0));
        return this;
    }

    public String getDefaultId() {
        String DefaultId = $(By.xpath("//tbody/tr[1]/td[1]")).getText();
        return DefaultId;
    }

    public boolean isIdCorrect(String expectedId) {
        TABLE_BODY.shouldBe(visible);
        SelenideElement firstId = $(By.xpath("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[1]"));
        firstId.shouldHave(text(expectedId));
        return expectedId.equals(firstId.getText());

    }
}
