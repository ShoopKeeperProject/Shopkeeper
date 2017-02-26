package com.example.shopkeeper.Manager;

import android.os.Handler;
import android.os.Looper;

import com.example.shopkeeper.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2017/2/18.
 */

public class ProductManager {

    private static ProductManager sProductRepo;
    private Handler mHandler;

    private ProductManager(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    public synchronized static ProductManager getInstance(){
        if (sProductRepo == null){
            sProductRepo = new ProductManager();
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
                products.add(new Product(2, 0, "Wine ",10.1, "", "Red wine"));
                products.add(new Product(3, 0, "Beer ",1.2, "", "ABC brand"));
                products.add(new Product(4, 0, "Hello ",3, "", "Testing product"));
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
