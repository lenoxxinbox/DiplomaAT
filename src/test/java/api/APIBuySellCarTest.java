package api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@Feature("Проверка покупки/продажи авто с помощью API-запросов")
public class APIBuySellCarTest {
    public String toAuthorize() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"password\": \"user\", \"username\": \"user@pflb.ru\"}")
                .when()
                .post("http://77.50.236.203:4879/login");

        return response.jsonPath().getString("access_token");
    }

    @Test
    @DisplayName("Проверка успешной покупки авто с помощью API-запроса")
    @Order(1)
    @Owner("Elena Dmitrienko")
    public void userBuyCar() {
        String token = toAuthorize();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://77.50.236.203:4879/user/1/buyCar/6")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    @DisplayName("Проверка успешной продажи авто с помощью API-запроса")
    @Order(2)
    @Owner("Elena Dmitrienko")
    public void userSellCar() {
        String token = toAuthorize();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://77.50.236.203:4879/user/1/sellCar/6")
                .then()
                .assertThat().statusCode(200);
    }
}
