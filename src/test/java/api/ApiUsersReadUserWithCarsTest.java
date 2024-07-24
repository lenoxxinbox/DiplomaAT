package api;


import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Feature("Проверка наличия пользователя с авто с помощью API-запросов")
public class ApiUsersReadUserWithCarsTest {
    public String toAuthorize() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"password\": \"user\", \"username\": \"user@pflb.ru\"}")
                .when()
                .post("http://77.50.236.203:4879/login");

        return response.jsonPath().getString("access_token");
    }

    @Test
    @DisplayName("API. Проверка получения информации о машинах пользователя")
    @Order(1)
    @Owner("Lapidus Vyacheslav")
    public void checkUserWithCars() {
        String token = toAuthorize();
        String userId = "3";
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://77.50.236.203:4879/user/"+userId+"/info")
                .then()
                .assertThat().statusCode(200);
    }
}
