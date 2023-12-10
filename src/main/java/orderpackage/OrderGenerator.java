package orderpackage;
import orderpackage.Order;

public class OrderGenerator {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;


    public static Order randomOrder(){
        return new Order("1", "2", "3", "4", "675435", 4, "5", "comment", new String[]{null});

    }
    public static Order randomOrderWithGreyColor(){
        return new Order("2", "3", "4", "5", "6754351", 1, "6", "comment1", new String[]{"grey"});

    }

    public static Order randomOrderWithBlackColor(){
        return new Order("3", "4", "5", "6", "6754352", 2, "1", "comment2", new String[]{"black"});

    }

    public static Order randomOrderWithBlackAndGreyColor(){
        return new Order("3", "4", "5", "6", "6754352", 2, "1", "comment2", new String[]{"black", "grey"});

    }

}
