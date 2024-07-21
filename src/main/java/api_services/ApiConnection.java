package api_services;

import io.restassured.response.Response;
import model.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiConnection {
    private String token;
    private String url;

    public ApiConnection(String url) {
        this.url = url;
    }

    public void authorize(String username, String password) {
        String authEndpoint = url + "login";
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(credentials)
                .when()
                .post(authEndpoint)
                .then()
                .extract()
                .response();

        token = response.jsonPath().getString("access_token");

    }


    public Response get(String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response post(String endpoint, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response put(String endpoint, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response delete(String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response createUser(User user) {
        return this.post(url + "user", user.toJson());
    }

    public Response getUser(int id) {
        return this.get(url + "user/" + id);
    }

    public Response editUser(int id, User user) {
        return this.put(url + "user/" + id, user.toJson());
    }

    public Response deleteUser(int id) {
        return this.delete(url + "user/" + id);
    }

}
