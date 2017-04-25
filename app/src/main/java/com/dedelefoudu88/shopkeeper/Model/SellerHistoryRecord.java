package com.dedelefoudu88.shopkeeper.Model;

import com.dedelefoudu88.shopkeeper.Utilities.JSONUtili;

import org.json.JSONObject;

/**
 * Created by Tony on 2017/2/26.
 */

public class SellerHistoryRecord {
    private double mTotalSales;
    private String mEmail;
    private String mUserName;

    public SellerHistoryRecord(JSONObject jsonObject){
        this.mTotalSales = JSONUtili.getInteger(jsonObject, "totalSales") / 100.0;
        this.mEmail = JSONUtili.getString(jsonObject, "email");
        this.mUserName = JSONUtili.getString(jsonObject, "name");
    }

    public double getTotalSales() {
        return mTotalSales;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getName(){
        return this.mUserName;
    }
}
