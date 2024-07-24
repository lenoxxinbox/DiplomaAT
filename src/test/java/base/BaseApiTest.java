package base;

import api_service.AuthorizationRequest;
import api_service.CarRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;


public class BaseApiTest {
    protected static String accessToken;
    protected static CarRequest carRequest;
    protected static AuthorizationRequest authRequest;

    @BeforeEach
    public void inizialization(){
        carRequest = new CarRequest();
    }

    @BeforeAll
    protected static void setUp() {
        authRequest = new AuthorizationRequest(
                ConfigReader.get("password"),
                ConfigReader.get("username"));
        RestAssured.baseURI = ConfigReader.get("baseApiURL");
        String authJson = authRequest.toJson();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(authJson)
                .post("/login");
        accessToken = response.jsonPath().getString("access_token");
    }

    protected static RequestSpecification requestSpec(String accessToken) {
        return new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
    }

    protected static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}
