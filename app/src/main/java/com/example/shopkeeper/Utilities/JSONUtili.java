package com.example.shopkeeper.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tony on 2017/3/6.
 */

public class JSONUtili {
    public static Double getDouble(JSONObject jsonObject, String key){
        try {
            if (!jsonObject.isNull(key)) {
                return jsonObject.getDouble(key);
            }
        } catch (JSONException ex){

        }
        return 0.0;
    }

    public static Integer getInteger(JSONObject jsonObject, String key){
        try {
            if (!jsonObject.isNull(key)) {
                return jsonObject.getInt(key);
            }
        } catch (JSONException ex){

        }
        return 0;
    }

    public static ArrayList<String> getStringArray(JSONObject jsonObject, String key){
        ArrayList<String> res = new ArrayList<>();
        try {
            if (!jsonObject.isNull(key)) {
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                for (int cnt = 0; cnt < jsonArray.length(); cnt++){
                    res.add(jsonArray.getString(cnt));
                }
            }
        } catch (JSONException ex){

        }
        return res;
    }

    public static String getFirstString(JSONObject jsonObject, String key){
        try {
            if (!jsonObject.isNull(key)) {
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                if (jsonArray.length() > 0){
                    return jsonArray.getString(0);
                }
            }
        } catch (JSONException ex){

        }
        return "";
    }

    public static boolean getBoolean(JSONObject jsonObject, String key, boolean defaultValue){
        boolean result = defaultValue;
        try {
            if (!jsonObject.isNull(key)) {
                Boolean value = jsonObject.getBoolean(key);
                if (value != null){
                    result =  value;
                }
            }
        } catch (JSONException ex){

        }
        return result;
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
