import courierpackage.Courier;
import courierpackage.CourierClient;
import courierpackage.CourierLogin;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static courierpackage.CourierGenerator.*;
import static courierpackage.CourierLogin.fromCourier;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class CourierTests {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private int id;
    private String message;
    private boolean ok;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера с данными, статус ответа 200, ответ OK")
    public void createCourier() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();

        Response response = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());

        Response loginResponse = courierClient.login(fromCourier(courier));

        id = loginResponse.path("id");
        ok = response.path("ok");

        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
        assertEquals(true, ok);
    }

    @Test
    @DisplayName("Создание двух одинаковых курьера")
    @Description("Создание двух одинаковых курьеров, статус ответа 409")
    public void createTwoTheSameCourier(){
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        Response response2 = courierClient.create(courier);
        assertEquals("Неверный статус код", SC_CONFLICT, response2.statusCode());


    }

    @Test
    @DisplayName("Создание курьера двух курьеров с одинаковым логином")
    @Description("Создание двух курьеров c одинаковым логином, статус ответа 409")
    public void createTwoCouriersWithSameLogin(){
        Courier courier1 = randomCourier();
        Courier courier2 = randomCourier();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier1);
        assertEquals("Неверный статус код", SC_CREATED,response.statusCode());
        Response loginResponse = courierClient.login(fromCourier(courier1));
        id = loginResponse.path("id");
        Response response2 = courierClient.create(courier2);
        message = response2.path("message");
        assertEquals("Неверное сообщение об ошибке","Этот логин уже используется.", message);
        assertEquals("Неверный статус код", SC_CONFLICT, response2.statusCode());


    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Создание курьера без логина, статус ответа 400")
    public void createCourierWithoutLogin(){
        Courier courier = courierWithoutLogin();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);

        message = response.path("message");
        assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
        assertEquals("Неверное сообщение об ошибке","Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @DisplayName("Создание курьера без имени")
    @Description("Создание курьера без имени, статус ответа 201")
    public void createCourierWithoutFirstName(){
        Courier courier = courierWithoutFirstName();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        Response loginResponse = courierClient.login(fromCourier(courier));
        id = loginResponse.path("id");

        message = response.path("message");
        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());

    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Создание курьера без пароля, статус ответа 400")
    public void createCourierWithoutPassword(){
        Courier courier = courierWithoutPassword();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);

        message = response.path("message");
        assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
        assertEquals("Неверное сообщение об ошибке","Недостаточно данных для создания учетной записи", message);
    }


    @After
    public void tearDown() {
        CourierClient courierClient = new CourierClient();
        Response delete = courierClient.delete(id);

    }
}
