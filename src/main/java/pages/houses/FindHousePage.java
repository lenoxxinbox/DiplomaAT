package pages.houses;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FindHousePage {
    private final static SelenideElement HOUSES_MENU = $x("//a[contains(text(), 'Houses')]");
    private final static SelenideElement HOUSES_MENU_FIND_HOUSE = $x("//a[contains(text(), 'Read one by ID')]");
    private final static SelenideElement FIND_STATUS = $x("//button[contains(text(), 'Status:')]");
    private final static SelenideElement ID_FIND = $x("//input[@id='house_input']");
    private final static SelenideElement TABLE_FIND = $x("//table[@class='tableHouse table table-striped table-bordered table-hover']");
    private final static SelenideElement FIND_BUTTON = $x("//button[contains(text(), 'Read')]");

    public FindHousePage goFindHouse() {
        HOUSES_MENU.click();
        HOUSES_MENU_FIND_HOUSE.click();
        return this;
    }
    public String getFindStatus() {
        return FIND_STATUS.getText();
    }

    public FindHousePage findHouse(String id) {
        ID_FIND.sendKeys(id);
        FIND_BUTTON.click();
        $(TABLE_FIND).shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
