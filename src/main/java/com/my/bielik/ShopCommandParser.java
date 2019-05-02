package com.my.bielik;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopCommandParser {

    private ShopController shopController = new ShopController();

    public String parseString(String string) throws IOException {
        JSONObject json = JsonReader.getJsonObject();

        if (string.startsWith(Command.PURCHASE)) {
            Pattern pattern = Pattern.compile(Command.PURCHASE + "\\s\\d{4}-\\d{2}-\\d{2}\\s(\\d|.)+\\s[A-Z]{3}\\s.*");
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                String[] container = string.split("\\s", 5);
                if (isDateIncorrect(container[1]))
                    System.out.println("\n\tinvalid date");
                else
                    try {
                        json.getJSONObject("rates").get(container[3]);
                        return shopController.purchase(container[4], container[2], container[3], container[1]); // name, price, currency, date
                    } catch (JSONException ex) {
                        System.out.println("\n\twrong currency");
                    }
            } else
                System.out.println("\n\tinvalid input");
        } else if (string.equals(Command.ALL))
            return shopController.all();
        else if (string.startsWith(Command.CLEAR)) {
            Pattern pattern = Pattern.compile(Command.CLEAR + "\\s\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                String[] container = string.split("\\s");
                if (isDateIncorrect(container[1]))
                    System.out.println("\n\tinvalid date");
                else
                    return shopController.clear(container[1]); // date
            } else
                System.out.println("\n\tinvalid input");
        } else if (string.startsWith(Command.REPORT)) {
            Pattern pattern = Pattern.compile(Command.REPORT + "\\s\\d{4}\\s[A-Z]{3}");
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                String[] container = string.split("\\s");
                try {
                    json.getJSONObject("rates").get(container[2]);
                    return shopController.report(container[1], container[2]); // date
                } catch (JSONException ex) {
                    System.out.println("\n\twrong currency");
                }
            } else
                System.out.println("\n\tinvalid input");
        } else
            System.out.println("\n\tinvalid input");
        return "";
    }

    private boolean isDateIncorrect(String date) {
        String[] container = date.split("-");
        if (Integer.valueOf(container[1]) > 12 || Integer.valueOf(container[1]) == 0)
            return true;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(container[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(container[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return Integer.valueOf(container[2]) == 0 || Integer.valueOf(container[2]) > calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private static class Command {
        private static final String PURCHASE = "purchase";
        private static final String ALL = "all";
        private static final String CLEAR = "clear";
        private static final String REPORT = "report";
    }
}
