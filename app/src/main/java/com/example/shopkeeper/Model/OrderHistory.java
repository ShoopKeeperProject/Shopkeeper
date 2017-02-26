package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/26.
 */

public class OrderHistory {
    private String mUserId;
    private OrderHistoryItem mItem;

    public OrderHistory(String mUserId, OrderHistoryItem mItem){
        this.mUserId = mUserId;
        this.mItem = mItem;
    }

    public String getUserId() {
        return mUserId;
    }

    public OrderHistoryItem getItem() {
        return mItem;
    }
}
