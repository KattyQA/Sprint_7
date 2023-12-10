import courierpackage.Courier;
import courierpackage.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static courierpackage.CourierGenerator.*;
import static courierpackage.CourierLogin.fromCourier;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CourierLoginTests {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private int id;
    private String message;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    @Test
    @DisplayName("Логин курьера")
    @Description("Логин курьера с валидными данными, статус ответа 200, ответ id")
    public void loginCourier() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();

        Response response = courierClient.create(courier);

        Response loginResponse = courierClient.login(fromCourier(courier));

        id = loginResponse.path("id");

        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
        System.out.println(loginResponse.body().asString());
        Response delete = courierClient.delete(id);
        assertEquals("Неверный статус код", SC_OK, delete.statusCode());
    }

    @Test
    @DisplayName("Логин курьера без логина")
    @Description("Логин курьера без логина, статус ответа 400")
    public void loginCourierWithoutLogin() {
        Courier courier = randomCourierWithoutLogin();
        CourierClient courierClient = new CourierClient();

        Response loginResponse = courierClient.login(fromCourier(courier));

        message = loginResponse.path("message");

        assertEquals("Неверный статус код", 400 , loginResponse.statusCode());
        assertEquals("Неверное сообщение об ошибке","Недостаточно данных для входа", message);

    }

    @Test
    @DisplayName("Логин курьера без пароля")
    @Description("Логин курьера без пароля, статус ответа 400")
    public void loginCourierWithoutPassword() {
        Courier courier = randomCourierWithoutPassword();
        CourierClient courierClient = new CourierClient();

        Response loginResponse = courierClient.login(fromCourier(courier));

        message = loginResponse.path("message");

        assertEquals("Неверный статус код", 400 , loginResponse.statusCode());
        assertEquals("Неверное сообщение об ошибке","Недостаточно данных для входа", message);

    }
    @Test
    @DisplayName("Логин курьера")
    @Description("Логин курьера с неверным логином, статус ответа 404")
    public void loginCourierWithWrongLogin() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();

        Response response = courierClient.create(courier);
        Response loginResponse1 = courierClient.login(fromCourier(courier));

        id = loginResponse1.path("id");

        courier.setLogin("35636");

        Response loginResponse2 = courierClient.login(fromCourier(courier));


        assertEquals("Неверный статус код", SC_NOT_FOUND, loginResponse2.statusCode());
        message = loginResponse2.path("message");
        assertEquals("Неверное сообщение об ошибке","Учетная запись не найдена", message);
        Response delete = courierClient.delete(id);
        assertEquals("Неверный статус код", SC_OK, delete.statusCode());
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Логин курьера с неверным паролем, статус ответа 404")
    public void loginCourierWithWrongPassword() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();

        Response response = courierClient.create(courier);
        Response loginResponse1 = courierClient.login(fromCourier(courier));

        id = loginResponse1.path("id");

        courier.setPassword("35636");

        Response loginResponse2 = courierClient.login(fromCourier(courier));


        assertEquals("Неверный статус код", SC_NOT_FOUND, loginResponse2.statusCode());
        message = loginResponse2.path("message");
        assertEquals("Неверное сообщение об ошибке","Учетная запись не найдена", message);
        Response delete = courierClient.delete(id);
        assertEquals("Неверный статус код", SC_OK, delete.statusCode());
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Логин курьера с несуществующим логином и паролем, статус ответа 404")
    public void loginCourierWithWrongPasswordAndParssword() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();

        Response response = courierClient.create(courier);
        Response loginResponse1 = courierClient.login(fromCourier(courier));

        id = loginResponse1.path("id");

        courier.setPassword("35636");
        courier.setLogin("35636");

        Response loginResponse2 = courierClient.login(fromCourier(courier));


        assertEquals("Неверный статус код", SC_NOT_FOUND, loginResponse2.statusCode());
        message = loginResponse2.path("message");
        assertEquals("Неверное сообщение об ошибке","Учетная запись не найдена", message);
        Response delete = courierClient.delete(id);
        assertEquals("Неверный статус код", SC_OK, delete.statusCode());
    }


}

