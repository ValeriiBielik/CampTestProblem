import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ShopCommandParser shopCommandParser = new ShopCommandParser();
        String s;
        while(true){
            s = in.nextLine();
            if (s.equals("end"))
                return;
            else
                try{
                    shopCommandParser.parseString(s);
                } catch (IOException ex){
                    ex.printStackTrace();
                }
        }
    }
}
