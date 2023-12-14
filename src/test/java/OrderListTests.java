import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import orderpackage.Order;
import orderpackage.OrderClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.apache.http.HttpStatus.*;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OrderListTests {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private List<Order> orders;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Получение списка заказов, статус ответа 200, ответ содержит список заказов")
    public void getOrderList(){
        OrderClient orderClient = new OrderClient();

        Response response = orderClient.orderList();
        assertEquals("Неверный статус код", SC_OK, response.statusCode());
        orders = response.path("orders.id");
        assertThat("orders", notNullValue());

    }
}

