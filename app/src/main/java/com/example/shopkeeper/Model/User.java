package com.example.shopkeeper.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dedel on 27/02/2017.
 */

public class User {

    String nName;

    public User(JSONObject jsonObject) throws JSONException{
        this.nName = jsonObject.getString("name");
    }

    public User(String name)
    {
        this.nName = name;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }
}
