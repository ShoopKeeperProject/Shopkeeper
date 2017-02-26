package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/18.
 */

public class Category {

    protected String mName;
    protected int mId;
    protected int mParentId;
    protected String mImageURL;
    protected boolean product;

    public Category( int mId, int mParentId, String mName, String mImageURL){
        this.mId = mId;
        this.mParentId = mParentId;
        this.mImageURL = mImageURL;
        this.mParentId = mParentId;
        this.mName = mName;
        this.product = false;
    }

    public String getmDescription() {
        return "";
    }

    public boolean isProduct() {
        return product;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmParentId() {
        return mParentId;
    }

    public void setmParentId(int mParentId) {
        this.mParentId = mParentId;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

    public double getmPrice() {
        return 0;
    }
}
