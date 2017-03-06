package com.example.shopkeeper.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dedel on 27/02/2017.
 */

public class ProductDescription implements Parcelable {
    private String mtitle;
    private String mdescription;
    public ProductDescription(String title, String description)
    {
        this.mtitle = title;
        this.mdescription = description;
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public ProductDescription createFromParcel(Parcel in) {
            return new ProductDescription(in);
        }

        public ProductDescription[] newArray(int size) {
            return new ProductDescription[size];
        }
    };

    // "De-parcel object
    public ProductDescription(Parcel in) {
        this.mtitle = in.readString();
        this.mdescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mtitle);
        dest.writeString(mdescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getMtitle() {
        return mtitle;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public void setMdescription(String mdescription) {
        this.mdescription = mdescription;
    }



}
