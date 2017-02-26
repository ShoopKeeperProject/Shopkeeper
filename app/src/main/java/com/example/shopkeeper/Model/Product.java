package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/18.
 */

public class Product extends Category{

    private String mDescription;
    private double mPrice;

    public Product( int mId, int mParentId, String mName,double mPrice, String mImageURL, String mDescription){
        super(mId, mParentId, mName, mImageURL);
        this.mDescription = mDescription;
        this.product = true;
        this.mPrice = mPrice;
    }
    @Override
    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public double getmPrice() {
        return mPrice;
    }
}
