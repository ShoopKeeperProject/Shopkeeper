package com.dedelefoudu88.shopkeeper.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dedel on 22/02/2017.
 */

public class ItemMakeSellListVew implements Parcelable {
    private String name;
    private int qt;
    private double pu;
    private double taxe;
    private String uid;

    public ItemMakeSellListVew(String productId, String name, int qt, double pu, double taxe) {
        this.name = name;
        this.qt = qt;
        this.pu = pu;
        this.taxe = taxe;
        this.uid = productId;
    }

    // "De-parcel object
    public ItemMakeSellListVew(Parcel in) {
        this.name = in.readString();
        this.qt = in.readInt();
        this.pu = in.readDouble();
        this.taxe = in.readDouble();
        this.uid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.qt);
        dest.writeDouble(this.pu);
        dest.writeDouble(this.taxe);
        dest.writeString(this.uid);
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

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {

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

    public double getTaxe() {
        return taxe;
    }

    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }


}
