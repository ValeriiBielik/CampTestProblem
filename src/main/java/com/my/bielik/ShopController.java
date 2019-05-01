package com.my.bielik;

import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

class ShopController {

    private List<ShopItem> items = new ArrayList<ShopItem>();

    void purchase(String name, String price, String currency, String date){
        items.add(new ShopItem(name, Double.parseDouble(price), currency, date));
        Collections.sort(items);
        System.out.println(getAllItemsInfo());
    }

    void all(){
        System.out.println(getAllItemsInfo());
    }

    void clear(String date){
        Calendar calendar = Calendar.getInstance();
        try{
            calendar.setTime(ShopItem.FORMAT.parse(date));
            for(int i = 0; i < items.size(); i++) {
                if (items.get(i).getDate().equals(calendar)) {
                    items.remove(i);
                    i++;
                }
            }
        } catch (ParseException ex){
            ex.printStackTrace();
        }

        System.out.println(getAllItemsInfo());
    }

    void report(String year, String currency) {
        double sum = 0;
        JSONObject json;
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.set(Calendar.YEAR, Integer.valueOf(year));

        try{
             json = JsonReader.getJsonObject();

            if (currency.equals(json.get("base"))){
                for (ShopItem item : items){
                    if (item.getDate().get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
                        if(currency.equals(item.getCurrency()))
                            sum += item.getPrice();
                        else
                            sum += item.getPrice() / Double.valueOf(json.getJSONObject("rates").get(item.getCurrency()).toString());
                    }
                }
            }
            else{
                for (ShopItem item : items){
                    if (item.getDate().get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
                        if(currency.equals(item.getCurrency()))
                            sum += item.getPrice();
                        else{
                            sum += item.getPrice() / Double.valueOf(json.getJSONObject("rates").get(item.getCurrency()).toString())
                                    * Double.valueOf(json.getJSONObject("rates").get(currency).toString());
                        }
                    }
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.printf("\n\t%.2f %s\n\n", sum, currency);
    }

    private String getAllItemsInfo(){
        StringBuilder s = new StringBuilder();
        Calendar temp = Calendar.getInstance();
        for (ShopItem item : items){
            if (temp.equals(item.getDate())){
                s.append("\n\t")
                        .append(item.getName())
                        .append(" ")
                        .append(item.getPrice())
                        .append(" ")
                        .append(item.getCurrency());
            }
            else{
                if (s.length() != 0){
                    s.append("\n");
                }
                s.append("\n\t")
                        .append(ShopItem.FORMAT.format(item.getDate().getTime()))
                        .append("\n\t")
                        .append(item.getName())
                        .append(" ")
                        .append(item.getPrice())
                        .append(" ")
                        .append(item.getCurrency());
                temp = item.getDate();
            }
        }
        s.append("\n");
        return s.toString();
    }
}