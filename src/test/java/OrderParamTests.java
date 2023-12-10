import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import orderpackage.Order;
import orderpackage.OrderClient;
import orderpackage.OrderGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderParamTests {

    private int expectedStatus;
    private Order order;


    public OrderParamTests(int expectedStatus, Order order){
        this.expectedStatus= expectedStatus;
        this.order = order;
    }

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {SC_CREATED, OrderGenerator.randomOrder()},
                {SC_CREATED, OrderGenerator.randomOrderWithBlackColor()},
                {SC_CREATED, OrderGenerator.randomOrderWithGreyColor()},
                {SC_CREATED, OrderGenerator.randomOrderWithBlackAndGreyColor()},
        };
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа с данными, статус ответа 200")
    public void createOrder() {
        OrderClient orderClient = new OrderClient();

        Response create = orderClient.create(order);
        assertEquals("Неверный статус код", expectedStatus, create.statusCode());

    }
}



