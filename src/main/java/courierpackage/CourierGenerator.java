package courierpackage;

public class CourierGenerator {
    public static Courier randomCourier(){
        return new Courier()
                .withLogin("fa1235")
                .withPassword("fa2315")
                .withFirstName("fa2141");
    }
    public static Courier randomCourierWithoutLogin(){
        return new Courier()
                .withLogin(null)
                .withPassword("fa231");
    }

    public static Courier randomCourierWithoutPassword(){
        return new Courier()
                .withLogin("fa231")
                .withPassword(null);
    }
    public static Courier courierWithoutLogin(){
        return new Courier()
                .withLogin(null)
                .withPassword("0020902")
                .withFirstName("GTTRR");
    }

    public static Courier courierWithoutPassword(){
        return new Courier()
                .withLogin("0020902")
                .withPassword(null)
                .withFirstName("frryrty");
    }

    public static Courier courierWithoutFirstName(){
        return new Courier()
                .withLogin("qqqqqq11")
                .withPassword("qqqqqfdhhddh11")
                .withFirstName(null);
    }





}
