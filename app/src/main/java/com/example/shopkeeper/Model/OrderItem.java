package com.example.shopkeeper.Model;

import java.util.ArrayList;

/**
 * Created by Tony on 2017/2/18.
 */

public class OrderItem {
    private String mOrderID;
    private Product mProduct;
    private int mQuantity;

    public OrderItem(String orderId, Product mProduct, int quantity){
        this.mOrderID = orderId;
        this.mProduct = mProduct;
        this.mQuantity = quantity;
    }

    public String getOrderID() {
        return mOrderID;
    }

    public Product getProduct() {
        return mProduct;
    }

    public int getQuantity() {
        return mQuantity;
    }
}
