package com.my.bielik;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

class JsonReader {

    static JSONObject getJsonObject() throws IOException{
        return JsonReader.readJsonFromUrl("http://data.fixer.io/api/latest?access_key=a269347001a84c70de8ccf5fd6194f3e");
    }

    static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            return new JSONObject(readAll(rd));
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}