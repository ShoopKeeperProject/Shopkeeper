package com.example.shopkeeper.Repository;

import android.os.Handler;
import android.os.Looper;

import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2017/2/18.
 */

public class ProductRepository {

    private static ProductRepository sProductRepo;
    private Handler mHandler;

    public ProductRepository(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    public synchronized static ProductRepository getInstance(){
        if (sProductRepo == null){
            sProductRepo = new ProductRepository();
        }
        return sProductRepo;
    }

    public void get(int parentId, final CallBack<List<Category>> callBack){
        if (callBack == null){
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<Category> products = new ArrayList<>();
                products.add(new Product(1, 0, "Wine",1, "", "Red wine"));
                products.add(new Product(2, 0, "Beer",1, "", "ABC brand"));
                products.add(new Product(3, 0, "Hello",1, "", "Testing product"));
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
