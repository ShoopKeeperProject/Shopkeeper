package com.example.shopkeeper.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dedel on 22/02/2017.
 */

public class ItemMakeSellListVew implements Parcelable {
    private String name;
    private int qt;
    private double pu;
    private static int idnb = 0;
    private int uid;

    public ItemMakeSellListVew(String name, int qt, double pu) {
        this.name = name;
        this.qt = qt;
        this.pu = pu;
        this.uid = this.idnb;
        this.idnb++;

    }

    // "De-parcel object
    public ItemMakeSellListVew(Parcel in) {
        this.name = in.readString();
        this.qt = in.readInt();
        this.pu = in.readDouble();
        this.uid = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.qt);
        dest.writeDouble(this.pu);
        dest.writeInt(this.uid);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public ItemMakeSellListVew createFromParcel(Parcel in) {
            return new ItemMakeSellListVew(in);
        }

        public ItemMakeSellListVew[] newArray(int size) {
            return new ItemMakeSellListVew[size];
        }
    };

    public int getId() {

        return this.uid;
    }

    public String getName() {
        return name;
    }

    public int getQt() {
        return qt;
    }

    public double getPu() {
        return pu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    @Override
    public int describeContents() {
        return 0;
    }




}
