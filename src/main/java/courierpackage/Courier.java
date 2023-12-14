package courierpackage;

public class Courier {
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String login;
    private String password;
    private String firstName;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Courier withLogin(String login) {
        this.login = login;
        return this;
    }

    public Courier withPassword(String password) {
        this.password = password;
        return this;
    }

    public Courier withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Courier withLoginAndPassword(String login, String password){
        this.login = login;
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
