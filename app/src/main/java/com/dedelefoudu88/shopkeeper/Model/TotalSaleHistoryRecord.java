package com.dedelefoudu88.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/26.
 */

public class TotalSaleHistoryRecord {
    private long mTime;
    private int mIncome;

    public TotalSaleHistoryRecord( long mTime, int mIncome){
        this.mTime = mTime;
        this.mIncome = mIncome;
    }

    public long getTime() {
        return mTime;
    }

    public int getIncome() {
        return mIncome;
    }
}
