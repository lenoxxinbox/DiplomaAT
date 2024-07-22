package api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@Feature("Проверка заселения/выселения пользователя с помощью API-запросов")
public class APISettleEvictHousesTest {
    public String toAutorize() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"password\": \"user\", \"username\": \"user@pflb.ru\"}")
                .when()
                .post("http://77.50.236.203:4879/login");

        return response.jsonPath().getString("access_token");
    }

    @Test
    @DisplayName("Проверка успешного заселения пользователя в дом с помощью API-запроса")
    @Order(2)
    @Owner("Elena Dmitrienko")
    public void userSettleHouse() {
        String token = toAutorize();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://77.50.236.203:4879/house/1/settle/1")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    @DisplayName("Проверка успешного выселения пользователя из дома с помощью API-запроса")
    @Order(1)
    @Owner("Elena Dmitrienko")
    public void userEvictHouse() {
        String token = toAutorize();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("http://77.50.236.203:4879/house/1/evict/1")
                .then()
                .assertThat().statusCode(200);
    }
}
