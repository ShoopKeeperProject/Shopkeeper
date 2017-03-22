package com.example.shopkeeper.Model;

import com.example.shopkeeper.Utilities.JSONUtili;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tony on 2017/2/26.
 */

public class ProductHistoryRecord {
    private int mTotal;
    private Product mProduct;

    public ProductHistoryRecord(JSONObject jsonObject) {
        this.mTotal = JSONUtili.getInteger(jsonObject, "total");

        try {
            JSONObject jsonObject1 = jsonObject.getJSONObject("product");
            mProduct = new Product(jsonObject1);
        } catch (JSONException ex) {

        }
    }



    public int getTotal(){
        return this.mTotal;
    }

    public Product getProduct(){
        return this.mProduct;
    }
}
