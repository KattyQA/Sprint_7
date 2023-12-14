package courierpackage;

public class CourierLogin {

    private String login;
    private final String password;
    public CourierLogin(String login, String password){
        this.login = login;
        this.password = password;
    }

    public static CourierLogin fromCourier(Courier courier){
        return new CourierLogin(courier.getLogin(), courier.getPassword());
    }

    @Override
    public String toString() {
        return "CourierLogin{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
