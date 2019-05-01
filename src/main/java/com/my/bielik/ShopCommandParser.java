package com.my.bielik;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopCommandParser {

    private ShopController shopController = new ShopController();

    public void parseString(String string) throws IOException{
        JSONObject json = JsonReader.getJsonObject();

        if(string.startsWith(Command.PURCHASE)){
            Pattern pattern = Pattern.compile(Command.PURCHASE + "\\s\\d{4}-\\d{2}-\\d{2}\\s(\\d|.)+\\s[A-Z]{3}\\s.*");
            Matcher matcher = pattern.matcher(string);
            if(matcher.matches()){
                String[] container = string.split("\\s", 5);
                try{
                    json.getJSONObject("rates").get(container[3]);
                    shopController.purchase(container[4], container[2], container[3], container[1]); // name, price, currency, date
                } catch (JSONException ex){
                    System.out.println("\n\twrong currency\n");
                }
            }
            else
                System.out.println("\n\tinvalid input\n");
        }
        else if(string.equals(Command.ALL)){
            shopController.all();
        }
        else if(string.startsWith(Command.CLEAR)){
            Pattern pattern = Pattern.compile(Command.CLEAR + "\\s\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(string);
            if(matcher.matches()){
                String[] container = string.split("\\s");
                shopController.clear(container[1]); // date
            }
            else
                System.out.println("\n\tinvalid input\n");
        }
        else if(string.startsWith(Command.REPORT)){
            Pattern pattern = Pattern.compile(Command.REPORT + "\\s\\d{4}\\s[A-Z]{3}");
            Matcher matcher = pattern.matcher(string);
            if(matcher.matches()){
                String[] container = string.split("\\s");
                try{
                    json.getJSONObject("rates").get(container[2]);
                    shopController.report(container[1], container[2]); // date
                } catch (JSONException ex){
                    System.out.println("\n\twrong currency\n");
                }
            }
            else
                System.out.println("\n\tinvalid input\n");
        }
        else{
            System.out.println("\n\tinvalid input\n");
        }
    }

    private static class Command {
        private static final String PURCHASE = "purchase";
        private static final String ALL = "all";
        private static final String CLEAR = "clear";
        private static final String REPORT = "report";
    }
}
