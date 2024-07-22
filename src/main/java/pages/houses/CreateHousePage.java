package pages.houses;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreateHousePage {
    private final static SelenideElement HOUSES_MENU = $x("//a[contains(text(), 'Houses')]");
    private final static SelenideElement HOUSES_MENU_CREATE_NEW = $x("//a[contains(text(), 'Create new')]");
    private final static SelenideElement FLOORS_HOUSE = $x("//input[@id='floor_send']");
    private final static SelenideElement PRICE_HOUSE = $x("//input[@id='price_send']");
    private final static SelenideElement WARM_AND_COVERED = $x("//input[@id='parking_first_send']");
    private final static SelenideElement WARM_AND_NO_COVERED = $x("//input[@id='parking_second_send']");
    private final static SelenideElement NO_WARM_AND_COVERED = $x("//input[@id='parking_third_send']");
    private final static SelenideElement NO_WARM_AND_NO_COVERED = $x("//input[@id='parking_fourth_send']");
    private final static SelenideElement BUTTON_PUSH = $x("//button[@class='tableButton btn btn-primary']");
    private final static SelenideElement CREATE_STATUS = $x("//button[contains(text(), 'Status')]");
    private final static SelenideElement STATUS_FIND_SUCCESS = $x("//button[contains(text(), 'Successfully')]");
    private final static SelenideElement STATUS_FIND_INVALID = $x("//button[contains(text(), 'Invalid')]");
    private final static SelenideElement NEW_ID_HOUSE = $x("//button[@class='newId btn btn-secondary']");

    public static String id;

    public CreateHousePage goCreateHouse() {
        HOUSES_MENU.click();
        HOUSES_MENU_CREATE_NEW.click();
        return this;
    }

    public String getCreateStatus() {
        return CREATE_STATUS.getText();
    }

    public String getFloor() {
        return FLOORS_HOUSE.getValue();
    }

    public String getNewId() {
        return NEW_ID_HOUSE.getText();
    }

    public CreateHousePage createHouse(String floors, String price, String places1,
                                       String places2, String places3, String places4) {
        FLOORS_HOUSE.sendKeys(floors);
        PRICE_HOUSE.sendKeys(price);
        WARM_AND_COVERED.sendKeys(places1);
        WARM_AND_NO_COVERED.sendKeys(places2);
        NO_WARM_AND_COVERED.sendKeys(places3);
        NO_WARM_AND_NO_COVERED.sendKeys(places4);
        BUTTON_PUSH.click();
        if (getFloor().isEmpty())
            $(STATUS_FIND_INVALID).shouldBe(visible, Duration.ofSeconds(10));
        else {
            $(STATUS_FIND_SUCCESS).shouldBe(visible, Duration.ofSeconds(10));
            id = getNewId().replaceAll("\\D+", "");
        }
        return this;
    }
}
