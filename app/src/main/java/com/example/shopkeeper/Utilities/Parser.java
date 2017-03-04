package com.example.shopkeeper.Utilities;

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
}
