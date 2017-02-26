package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/26.
 */

public class OrderHistoryItem {

    private String mShoppingCartID;
    private String mStaffID;
    private String mProductID;
    private int mQuantity;
    private double mPricePerUnit;

    public String getStaffID() {
        return mStaffID;
    }

    public String getProductID() {
        return mProductID;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public double getPricePerUnit() {
        return mPricePerUnit;
    }
}
