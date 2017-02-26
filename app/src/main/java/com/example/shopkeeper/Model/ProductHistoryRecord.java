package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/26.
 */

public class ProductHistoryRecord {
    private String mUserId;
    private String mUserName;

    private long mTime;
    private Product mProduct;
    private int mQuantity;

    public ProductHistoryRecord(String mUserId, String mUserName, Product mProduct,  long mTime, int mQuantity){
        this.mUserId = mUserId;
        this.mUserName = mUserName;
        this.mProduct = mProduct;
        this.mTime = mTime;
        this.mQuantity = mQuantity;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public long getTime() {
        return mTime;
    }

    public Product getProduct() {
        return mProduct;
    }

    public int getQuantity() {
        return mQuantity;
    }
}
