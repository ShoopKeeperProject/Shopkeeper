package com.example.shopkeeper.Model;

import com.example.shopkeeper.Utilities.JSONUtili;
import com.example.shopkeeper.Utilities.Parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dedel on 27/02/2017.
 */

public class User {

    String mEmail;


    public User(JSONObject jsonObject) throws JSONException{
        this.mEmail = JSONUtili.getString(jsonObject, "email");
    }

    public User(String name)
    {
        this.mEmail = name;
    }

    public String getnName() {
        return mEmail;
    }

    public void setnName(String nName) {
        this.mEmail = nName;
    }
}
