import com.my.bielik.ShopCommandParser;
import org.junit.Test;


import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class ShopCommandParserTest {
    @Test
    public void testCommandPurchase() throws IOException {
        ShopCommandParser shopCommandParser = new ShopCommandParser();

        String s = shopCommandParser.parseString("purchase 2019-04-25 2 USD T-shirt");
        assertEquals("\n\t" +
                "2019-04-25" + "\n\t" +
                "T-shirt 2.0 USD\n", s);
    }

    @Test
    public void testCommandAll() throws IOException {
        ShopCommandParser shopCommandParser = new ShopCommandParser();

        String s = shopCommandParser.parseString("all");
        assertEquals("\n", s);
    }

    @Test
    public void testCommandClear() throws IOException {
        ShopCommandParser shopCommandParser = new ShopCommandParser();

        String s = shopCommandParser.parseString("clear 2019-04-27");
        assertEquals("\n", s);
    }

    @Test
    public void testCommandReport() throws IOException {
        ShopCommandParser shopCommandParser = new ShopCommandParser();

        String s = shopCommandParser.parseString("report 2019 UAH");
        assertEquals("\n\t0,00 UAH\n", s);
    }

}
