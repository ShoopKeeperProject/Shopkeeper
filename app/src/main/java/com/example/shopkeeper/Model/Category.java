package com.example.shopkeeper.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.shopkeeper.Utilities.JSONUtili;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tony on 2017/2/18.
 */

public class Category  implements Parcelable{

    protected String mId;
    protected String mParentId;
    protected String mName;
    protected String mImageURL;
    protected boolean product;
    protected boolean mIsEnable = true;

    public Category(JSONObject obj) throws JSONException{
        this(obj.getString("id"), obj.getString("parentId"), obj.getString("name"), JSONUtili.getFirstString(obj, "imageUrl"));
    }

    public Category( String mId, String mParentId, String mName, String mImageURL){
        this.mId = mId;
        this.mParentId = mParentId;
        this.mName = mName;
        this.mImageURL = mImageURL;
        this.product = false;
    }

    // "De-parcel object
    public Category(Parcel in)
    {
        mId = in.readString();
        mParentId = in.readString();
        mName = in.readString();
        mImageURL = in.readString();
        boolean[] value = new boolean[1];
        in.readBooleanArray(value);
        mIsEnable = value[0];
        this.product = false;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(mId);
        dest.writeString(mParentId);
        dest.writeString(mName);
        dest.writeString(mImageURL);
        boolean[]value = new boolean[1];
        value[0] = mIsEnable;
        dest.writeBooleanArray(value);
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

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmParentId() {
        return mParentId;
    }

    public void setmParentId(String mParentId) {
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

    public boolean isEnable() {
        return mIsEnable;
    }

    public void setIsEnable(boolean mIsEnable) {
        this.mIsEnable = mIsEnable;
    }
}
