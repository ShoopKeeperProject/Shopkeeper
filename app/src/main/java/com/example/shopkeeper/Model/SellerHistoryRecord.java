package com.example.shopkeeper.Model;

import com.example.shopkeeper.Utilities.JSONUtili;

import org.json.JSONObject;

/**
 * Created by Tony on 2017/2/26.
 */

public class SellerHistoryRecord {
    private int mTotalSales;
    private String mEmail;

    public SellerHistoryRecord(JSONObject jsonObject){
        this.mTotalSales = JSONUtili.getInteger(jsonObject, "totalSales");
        this.mEmail = JSONUtili.getString(jsonObject, "email");
    }

    public int getTotalSales() {
        return mTotalSales;
    }

    public String getEmail() {
        return mEmail;
    }
}
