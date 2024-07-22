package pages.houses;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HousesReadAll {
    private static final SelenideElement TABLE_ID = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[1]");
    private static final SelenideElement TABLE_FLOOR = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[2]");
    private static final SelenideElement TABLE_PRICE = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[3]");
    private static final SelenideElement TABLE_LODGERS_ID = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[5]/table/tbody/tr[1]/td[1]");
    private static final SelenideElement TABLE_LODGERS_FIRST_NAME = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[5]/table/tbody/tr[1]/td[2]");
    private static final SelenideElement TABLE_LODGERS_LAST_NAME = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[5]/table/tbody/tr[1]/td[3]");
    private static final SelenideElement TABLE_LODGERS_AGE = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[5]/table/tbody/tr[1]/td[4]");
    private static final SelenideElement TABLE_LODGERS_SEX = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[5]/table/tbody/tr[1]/td[5]");
    private static final SelenideElement TABLE_LODGERS_MONEY = $x("//*[@id=\"root\"]/div/section/div/table/tbody/tr[1]/td[5]/table/tbody/tr[1]/td[6]");
    private  String housesID;

}
