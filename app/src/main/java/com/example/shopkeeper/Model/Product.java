package com.example.shopkeeper.Model;

/**
 * Created by Tony on 2017/2/18.
 */

public class Product extends Category{

    private String mDescription;

    public Product(String mName) {
        super(mName);
    }

    public Product( String mId, String mParentId, String mName, String mImageURL, String mDescription){
        super(mName);
        this.mId = mId;
        this.mParentId = mParentId;
        this.mImageURL = mImageURL;
        this.mParentId = mParentId;
        this.mDescription = mDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
