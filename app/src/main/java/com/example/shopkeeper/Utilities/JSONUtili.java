package com.example.shopkeeper.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

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
