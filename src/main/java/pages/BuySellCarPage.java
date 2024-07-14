package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class BuySellCarPage {
    private final SelenideElement userID = $(id("id_send"));
    private final SelenideElement carID = $(id("car_send"));
    private final SelenideElement usersMenu = $(xpath("//a[text()='Users']"));
    private final SelenideElement buySellCarMenu = $(xpath("//a[text()='Buy or sell car']"));
    private final SelenideElement buyRadiobutton = $(xpath("//input[@value='buyCar']"));
    private final SelenideElement sellRadiobutton = $(xpath("//input[@value='sellCar']"));
    private final SelenideElement clickPush = $(xpath("//button[@class='tableButton btn btn-primary']"));
    private final SelenideElement resultStatus = $(xpath("//button[@class='status btn btn-secondary']"));

    public BuySellCarPage goToBuySellCarMenu () {
        usersMenu.click();
        buySellCarMenu.click();
        return this;
    }

    public BuySellCarPage userIDInput(String id) {
        userID.click();
        userID.sendKeys(id);
        return this;
    }

    public BuySellCarPage carIDInput(String id) {
        carID.click();
        carID.sendKeys(id);
        return this;
    }

    public BuySellCarPage buy () {
        buyRadiobutton.click();
        return this;
    }

    public BuySellCarPage sell () {
        sellRadiobutton.click();
        return this;
    }

    public BuySellCarPage push () {
        Configuration.timeout = 50000;
        clickPush.shouldBe(visible);
        System.out.println("До нажатия кнопки Push");
        clickPush.click();
        System.out.println("После нажатия кнопки Push");
        return this;
    }

    public String getResultMessage() {
        System.out.println(resultStatus.shouldBe(Condition.visible).getText());
        return resultStatus.shouldBe(Condition.visible).getText();
    }
}

