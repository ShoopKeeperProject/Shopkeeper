package com.example.shopkeeper.Model;

import java.util.ArrayList;

/**
 * Created by Tony on 2017/2/18.
 */

public class Order {
    private String mOrderID;
    private ArrayList<String> mProduct;
    private long mOrderTime;

    public String getmOrderID() {
        return mOrderID;
    }

    public void setmOrderID(String mOrderID) {
        this.mOrderID = mOrderID;
    }

    public ArrayList<String> getmProduct() {
        return mProduct;
    }

    public void setmProduct(ArrayList<String> mProduct) {
        this.mProduct = mProduct;
    }

    public long getmOrderTime() {
        return mOrderTime;
    }

    public void setmOrderTime(long mOrderTime) {
        this.mOrderTime = mOrderTime;
    }
}
