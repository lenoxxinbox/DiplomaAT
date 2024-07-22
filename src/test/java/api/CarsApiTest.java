package api;

import base.BaseApiTest;
import io.restassured.response.Response;
import model.Car;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsApiTest extends BaseApiTest {
    private static String carId;

    @Test
    @Order(1)
    @DisplayName("POST/Успешное создание автомобиля")
    public void checkPostCarSuccessful() {
        Car car = new Car("Diesel", "Tesla", "XXL", 200.00);
        Response postResponse = carRequest
                .createCar(car,requestSpec(accessToken))
                .then().log().all()
                .spec(responseSpec(201))
                .extract()
                .response();
        carId = postResponse.jsonPath().getString("id");
        Car createCar = postResponse.as(Car.class);
        assertTrue(carRequest.validateCarResponse(createCar, car), "Данные из ответа не совпадают");
    }

    @Test
    @Order(2)
    @DisplayName("GET/Получение автомобиля")
    public void checkGetCarSuccessful() {
        carRequest
                .getCar(carId,requestSpec(accessToken))
                .then().log().all()
                .spec(responseSpec(200));
    }

    @Test
    @Order(3)
    @DisplayName("PUT/Изменение автомобиля")
    public void checkPutCarSuccessful() {
        Car updatedCar = new Car("Electric", "Lada", "S", 300.00);
        Response putResponse = carRequest
                .updateCar(updatedCar, carId, requestSpec(accessToken))
                .then().log().all()
                .spec(responseSpec(202))
                .extract()
                .response();
        Car createCar = putResponse.as(Car.class);
        assertTrue(carRequest.validateCarResponse(createCar, updatedCar), "Данные из ответа не совпадают");
    }

    @Test
    @Order(4)
    @DisplayName("DELETE/Удаление автомобиля")
    public void checkDeleteCarSuccessful() {
        carRequest
                .deleteCar(carId,requestSpec(accessToken))
                .then().log().all()
                .spec(responseSpec(204));
    }

    @Test
    @Order(5)
    @DisplayName("POST/Создание автомобиля с пустыми полями")
    public void checkPostCarUnSuccessful() {
        Car car = new Car("", "", "", null);
        carRequest
                .createCar(car,requestSpec(accessToken))
                .then().log().all()
                .spec(responseSpec(400));
    }
}
