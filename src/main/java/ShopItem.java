import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class ShopItem implements Comparable<ShopItem>{

    static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-mm-dd");
    private double price;
    private String name;
    private String currency;
    private Calendar calendar;

    ShopItem(String name, double price, String currency, String purchaseDate) {
        this.price = price;
        this.name = name;
        this.currency = currency;
        calendar = Calendar.getInstance();
        try {
            calendar.setTime(FORMAT.parse(purchaseDate));
        } catch (ParseException ex){
            ex.printStackTrace();
        }
    }

    double getPrice() {
        return price;
    }

    String getName() {
        return name;
    }

    String getCurrency() {
        return currency;
    }

    Calendar getDate() {
        return calendar;
    }

    public int compareTo(ShopItem o) {
        return calendar.compareTo(o.calendar);
    }

}
