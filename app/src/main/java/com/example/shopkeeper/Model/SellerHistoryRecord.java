package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/26.
 */

public class SellerHistoryRecord {
    private String mUserId;
    private String mUserName;

    private long mTime;
    private double mIncome;

    public SellerHistoryRecord(String mUserId, String mUserName, long mTime,  double mIncome){
        this.mUserId = mUserId;
        this.mUserName = mUserName;
        this.mTime = mTime;
        this.mIncome = mIncome;
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

    public double getIncome() {
        return mIncome;
    }
}
