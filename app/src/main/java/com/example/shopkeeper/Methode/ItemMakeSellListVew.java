package com.example.shopkeeper.Methode;

/**
 * Created by dedel on 22/02/2017.
 */

public class ItemMakeSellListVew {
    private String name;
    private int qt;
    private double pu;
    private static int idnb = 0;

    public void setId(int id) {
        this.id = id;
    }

    public ItemMakeSellListVew(String name, int qt, double pu) {
        this.name = name;
        this.qt = qt;
        this.pu = pu;
        this.id = this.idnb;
        this.idnb++;

    }

    public int getId() {

        return id;
    }

    private int id;

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
}
