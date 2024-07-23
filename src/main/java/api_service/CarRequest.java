package api_service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Car;
import static io.restassured.RestAssured.given;

public class CarRequest {

    public Response createCar(Car car, RequestSpecification requestSpec) {
        String carJson = car.toJson();
        return given()
                .spec(requestSpec)
                .body(carJson)
                .when()
                .post("/car");
    }

    public Response deleteCar(String carId, RequestSpecification requestSpec) {
        return given()
                .spec(requestSpec)
                .pathParam("carId", carId)
                .when()
                .delete("/car/{carId}");
    }

    public Response getCar(String carId, RequestSpecification requestSpec) {
        return given()
                .spec(requestSpec)
                .pathParam("carId", carId)
                .when()
                .get("/car/{carId}");
    }

    public Response updateCar(Car updateCar, String carId, RequestSpecification requestSpec) {
        String carJson = updateCar.toJson();
        return given()
                .spec(requestSpec)
                .pathParam("carId", carId)
                .body(carJson)
                .when()
                .put("/car/{carId}");
    }

    public boolean  validateCarResponse(Car actualCar, Car expectedCar) {
        return actualCar.getEngineType().equals(expectedCar.getEngineType()) &&
                actualCar.getMark().equals(expectedCar.getMark()) &&
                actualCar.getModel().equals(expectedCar.getModel()) &&
                actualCar.getPrice().equals(expectedCar.getPrice());
    }
}
