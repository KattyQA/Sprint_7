package courierpackage;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String COURIER_URL = "api/v1/courier";
    private static final String COURIER_LOGIN_URL = "api/v1/courier/login";
    private static final String COURIER_DELETE_URL = "/api/v1/courier/";

    @Step("Создание курьера {courier}")
    public Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER_URL);
    }

    @Step("Авторизация курьером с логином и паролем {courierLogin}")
    public Response login(CourierLogin courierLogin) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierLogin)
                .when()
                .post(COURIER_LOGIN_URL);
    }

    @Step("Удаление курьера")
    public Response delete(int id){
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_DELETE_URL+id);
    }
}
