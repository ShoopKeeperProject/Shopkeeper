package com.example.shopkeeper.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tony on 2017/2/18.
 */

public class Category  implements Parcelable{

    protected int mId;
    protected int mParentId;
    protected String mName;
    protected String mImageURL;
    protected boolean product;

    public Category( int mId, int mParentId, String mName, String mImageURL){
        this.mId = mId;
        this.mParentId = mParentId;
        this.mName = mName;
        this.mImageURL = mImageURL;
        this.product = false;
    }

    // "De-parcel object
    public Category(Parcel in)
    {
        mId = in.readInt();
        mParentId = in.readInt();
        mName = in.readString();
        mImageURL = in.readString();
        this.product = false;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(mId);
        dest.writeInt(mParentId);
        dest.writeString(mName);
        dest.writeString(mImageURL);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
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
