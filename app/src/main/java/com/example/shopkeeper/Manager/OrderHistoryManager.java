package com.example.shopkeeper.Manager;

import android.os.Handler;
import android.os.Looper;

import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.Model.ProductHistoryRecord;
import com.example.shopkeeper.Model.SellerHistoryRecord;
import com.example.shopkeeper.Model.TotalSaleHistoryRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2017/2/26.
 */

public class OrderHistoryManager {

    private static OrderHistoryManager sManager;
    private Handler mHandler;

    private OrderHistoryManager(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    public synchronized static OrderHistoryManager getInstance(){
        if (sManager == null){
            sManager = new OrderHistoryManager();
        }
        return sManager;
    }

    public void getTopTenProduct(long fromTime, long toTime, final CallBack<List<ProductHistoryRecord>> callBack){
        if (callBack == null){
            return;
        }

        final ArrayList<ProductHistoryRecord> historyRecords = new ArrayList<>();
        historyRecords.add(createRecord("Laptop", "Computer", "Who am I", 0, 100));
        historyRecords.add(createRecord("Battery", "Unknown", "Robot", 0, 5));

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(historyRecords, null);
            }
        });
    }

    public void getProductHistory(long fromTime, long toTime, final CallBack<List<TotalSaleHistoryRecord>> callBack){
        if (callBack == null){
            return;
        }

        final ArrayList<TotalSaleHistoryRecord> historyRecords = new ArrayList<>();
        historyRecords.add(new TotalSaleHistoryRecord(fromTime, 300));
        historyRecords.add(new TotalSaleHistoryRecord(fromTime, 600));
        historyRecords.add(new TotalSaleHistoryRecord(fromTime, 400));

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(historyRecords, null);
            }
        });
    }

    public void getSellerHistory(long fromTime, long toTime, final CallBack<List<SellerHistoryRecord>> callBack){
        if (callBack == null){
            return;
        }

        final ArrayList<SellerHistoryRecord> historyRecords = new ArrayList<>();
        long period = (toTime - fromTime) / 4;
        historyRecords.add(new SellerHistoryRecord("", "Alex", fromTime, 300));
        historyRecords.add(new SellerHistoryRecord("", "Betty", fromTime + period, 700));
        historyRecords.add(new SellerHistoryRecord("", "Chris", fromTime + 2*period, 90));
        historyRecords.add(new SellerHistoryRecord("", "David", fromTime + 3*period, 0));
        historyRecords.add(new SellerHistoryRecord("", "E", toTime, 20));

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(historyRecords, null);
            }
        });
    }

    private ProductHistoryRecord createRecord(String name, String desc, String userName, long time, int qut){

        Product product = new Product(0, 1, name,1.0,20, "", desc); // need to be review
        ProductHistoryRecord productHistoryRecord = new ProductHistoryRecord("", userName, product, time, qut);
        return productHistoryRecord;
    }


}
