package api;

import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class APIFindHouseTest {
    public String toAuthorize() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"password\": \"user\", \"username\": \"user@pflb.ru\"}")
                .when()
                .post("http://77.50.236.203:4879/login");

        return response.jsonPath().getString("access_token");
    }

    @Test
    @DisplayName("Проверка успешного поиска дома с помощью API-запроса")
    @Owner("Кузнецов Александр")
    public void findHouse() {
        String token = toAuthorize();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://77.50.236.203:4879/house/1")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
