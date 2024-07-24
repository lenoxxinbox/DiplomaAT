package api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Feature("Проверка добавления денег пользователю с помощью API-запросов")
public class ApiUsersAddMoneyTest {
    public String toAuthorize() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"password\": \"user\", \"username\": \"user@pflb.ru\"}")
                .when()
                .post("http://77.50.236.203:4879/login");

        return response.jsonPath().getString("access_token");
    }

    @Test
    @DisplayName("API. Проверка успешного добавления денег пользователю")
    @Order(1)
    @Owner("Lapidus Vyacheslav")
    public void addMoneyToUser() {
        String token = toAuthorize();
        String userId = "3";
        String amount = "300";
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://77.50.236.203:4879/user/" +userId+"/money/"+ amount)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    @DisplayName("API. Проверка добавления денег несуществующему пользователю")
    @Order(2)
    @Owner("Lapidus Vyacheslav")
    public void addMoneyToUserNotExist() {
        String token = toAuthorize();
        String userId = "9090909090";
        String amount = "300";
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://77.50.236.203:4879/user/" +userId+"/money/"+ amount)
                .then()
                .assertThat().statusCode(404);
    }

    @Test
    @DisplayName("API. Проверка добавления денег пользователю, ввод некорректных значений(money)")
    @Order(3)
    @Owner("Lapidus Vyacheslav")
    public void addMoneyToUserIncorrectValues() {
        String token = toAuthorize();
        String userId = "3";
        String amount = "-1";
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://77.50.236.203:4879/user/" +userId+"/money/"+ amount)
                .then()
                .assertThat().statusCode(400);
    }
}
