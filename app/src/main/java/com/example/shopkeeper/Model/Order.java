package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/26.
 */

public class Order {
    private int mAmount;
    private double mPrice;
    private String mProductId;

    public Order(int amount, double mPrice, String mProductId){
        this.mAmount = amount;
        this.mPrice = mPrice;
        this.mProductId = mProductId;
    }

    public int getAmount() {
        return mAmount;
    }

    public double getPrice() {
        return mPrice;
    }

    public String getProductId() {
        return mProductId;
    }
}
