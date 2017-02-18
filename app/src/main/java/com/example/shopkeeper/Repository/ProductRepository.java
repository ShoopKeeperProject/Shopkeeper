package com.example.shopkeeper.Repository;

import android.os.Handler;
import android.os.Looper;

import com.example.shopkeeper.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2017/2/18.
 */

public class ProductRepository {

    private static ProductRepository sProductRepo;
    private Handler mHandler;

    private ProductRepository(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    public synchronized static ProductRepository getInstance(){
        if (sProductRepo == null){
            sProductRepo = new ProductRepository();
        }
        return sProductRepo;
    }

    public void get(String parentId, final CallBack<List<Product>> callBack){
        if (callBack == null){
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<Product> products = new ArrayList<>();
                products.add(new Product("0", null, "Wine", "", "Red wine"));
                products.add(new Product("1", null, "Beer", "", "ABC brand"));
                products.add(new Product("2", null, "Hello", "", "Testing product"));
                callBack.onResponse(products, null);
            }
        });
    }

    public void update(List<Product> products, final CallBack<Void> callBack){
        if (callBack == null){
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(null, null);
            }
        });
    }

    public void delete(List<Product> products, final CallBack<Void> callBack){
        if (callBack == null){
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(null, null);
            }
        });
    }
}
