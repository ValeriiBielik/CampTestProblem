import com.my.bielik.ShopCommandParser;
import org.junit.Test;

import java.io.IOException;

public class ShopCommandParserTest {
    @Test
    public void testCommandParser() throws IOException {
        ShopCommandParser shopCommandParser = new ShopCommandParser();
        System.out.println("purchase 2019-04-25 2 USD T-shirt");
        shopCommandParser.parseString("purchase 2019-04-25 2 USD T-shirt");

        System.out.println("purchase 2019-04-25 12 EUR “Photo Frame”");
        shopCommandParser.parseString("purchase 2019-04-25 12 EUR “Photo Frame”");

        System.out.println("purchase 2019-04-27 4.75 EUR Beer");
        shopCommandParser.parseString("purchase 2019-04-27 4.75 EUR Beer");

        System.out.println("purchase 2019-04-26 2.5 PLN Sweets");
        shopCommandParser.parseString("purchase 2019-04-26 2.5 PLN Sweets");

        System.out.println("all");
        shopCommandParser.parseString("all");

        System.out.println("clear 2019-04-27");
        shopCommandParser.parseString("clear 2019-04-27");

        System.out.println("report 2019 UAH");
        shopCommandParser.parseString("report 2019 UAH");
    }
}
