package api;

import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.HouseExample;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import static io.restassured.RestAssured.given;

public class APICreateHouseTest {
    PodamFactory factory = new PodamFactoryImpl();
    HouseExample houseExample = factory.manufacturePojo(HouseExample.class);

    public String toAuthorize() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"password\": \"user\", \"username\": \"user@pflb.ru\"}")
                .when()
                .post("http://77.50.236.203:4879/login");
        return response.jsonPath().getString("access_token");
    }

    @Test
    @DisplayName("Проверка успешного создания дома с помощью API-запроса")
    @Owner("Кузнецов Александр")
    public void createHouse() {
        String token = toAuthorize();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(houseExample)
                .post("http://77.50.236.203:4879/house")
                .then()
                .log().all()
                .assertThat().statusCode(201);
    }
}
