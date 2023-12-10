package orderpackage;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String ORDER_URL = "/api/v1/orders?limit=10&page=0";


    @Step("Создание заказа {order}")
    public Response create(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDER_URL);
    }
    @Step("Список заказов {orderList}")
    public Response orderList() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .get(ORDER_URL);


    }

}

