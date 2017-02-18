package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/18.
 */

public class Product {

    private String mName;
    private String mImageURL;
    private String mDescription;
    private String mId;
    private String mParentId;

    public Product( String mId, String mParentId, String mName, String mImageURL, String mDescription){
        this.mId = mId;
        this.mParentId = mParentId;
        this.mName = mName;
        this.mImageURL = mImageURL;
        this.mParentId = mParentId;
        this.mDescription = mDescription;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getUmageURL() {
        return mImageURL;
    }

    public void setUmageURL(String mUmageURL) {
        this.mImageURL = mUmageURL;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getParentId() {
        return mParentId;
    }

    public void setParentId(String mParentId) {
        this.mParentId = mParentId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
