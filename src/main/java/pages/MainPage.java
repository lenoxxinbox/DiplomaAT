package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private static final SelenideElement CARS_DROPDOWN = $x("//a[text() = 'Cars']");
    private static final SelenideElement CARS_READ_ALL = $x("//a[text() = 'Read all']");
    private static final SelenideElement CARS_CREATE_NEW = $x("//a[text() = 'Create new']");

    public void openCarsReadAll(){
        CARS_DROPDOWN.click();
        CARS_READ_ALL.click();
    }

    public void openCarsCreateNew() {
        CARS_DROPDOWN.shouldBe(visible).click();
        CARS_CREATE_NEW.shouldBe(visible).click();
    }


}
