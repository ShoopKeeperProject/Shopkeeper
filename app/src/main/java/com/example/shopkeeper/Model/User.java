package com.example.shopkeeper.Model;

/**
 * Created by dedel on 27/02/2017.
 */

public class User {

    String nName;

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
