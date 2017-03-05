package com.example.shopkeeper.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tony on 2017/3/4.
 */

public class Parser {

    public static double parseDouble(String data, double value){
        double result = value;
        try {
            result = Double.parseDouble(data);
        } catch (NumberFormatException ex){

        }

        return result;
    }

    public static boolean isEmpty(String string){
        return string == null || string.isEmpty();
    }

    public static Double getDouble(JSONObject jsonObject, String key){
        try {
            if (!jsonObject.isNull(key)) {
                return jsonObject.getDouble(key);
            }
        } catch (JSONException ex){

        }
        return 0.0;
    }

    public static String getString(JSONObject jsonObject, String key){
        try {
            if (!jsonObject.isNull(key)) {
                return jsonObject.getString(key);
            }
        } catch (JSONException ex){

        }
        return "";
    }
}
