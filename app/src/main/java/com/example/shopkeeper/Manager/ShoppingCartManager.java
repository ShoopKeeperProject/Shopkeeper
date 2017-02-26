package com.example.shopkeeper.Manager;

import android.os.Handler;
import android.os.Looper;

import com.example.shopkeeper.Model.OrderItem;
import com.example.shopkeeper.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2017/2/26.
 */

public class ShoppingCartManager {

    private Handler mHandler;
    private static ShoppingCartManager sManager;

    private ArrayList<OrderItem> mOrderList;   //change to database later

    private ShoppingCartManager(){
        mHandler = new Handler(Looper.getMainLooper());
        mOrderList = new ArrayList();

        Product wine = new Product(1, 0, "Wine",1.0, "", "Testing product");
        Product apple = new Product(2, 0, "Apple",1.0, "", "Testing product");

        mOrderList.add(new OrderItem("0", wine, 3));
        mOrderList.add(new OrderItem("1", apple, 1));
    }

    public synchronized static ShoppingCartManager getInstance(){
        if (sManager == null){
            sManager = new ShoppingCartManager();
        }
        return sManager;
    }

    public void updateOrder(final OrderItem order, final CallBack<OrderItem> callBack){
        if (callBack == null){
            return;
        }

        OrderItem oldOrder = null;
        for (OrderItem orderItem : mOrderList){
            if (orderItem.getOrderID().equals(order.getOrderID())){
                oldOrder = orderItem;
                break;
            }
        }
        if (oldOrder != null){
            mOrderList.remove(oldOrder);
        }
        mOrderList.add(order);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(order, null);
            }
        });
    }

    public void deleteOrder(final OrderItem order, final CallBack<Void> callBack){
        if (callBack == null){
            return;
        }

        OrderItem oldOrder = null;
        for (OrderItem orderItem : mOrderList){
            if (orderItem.getOrderID().equals(order.getOrderID())){
                oldOrder = orderItem;
                break;
            }
        }
        if (oldOrder != null){
            mOrderList.remove(oldOrder);
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(null, null);
            }
        });
    }

    public void submitOrder(final CallBack<Void> callBack){
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

    public void getAllOrders(final CallBack<List<OrderItem>> callBack){
        if (callBack == null){
            return;
        }

        final ArrayList<OrderItem> orderList = new ArrayList<>();
        for (OrderItem orderItem : mOrderList){
            orderList.add(new OrderItem(orderItem.getOrderID(), orderItem.getProduct(), orderItem.getQuantity()));
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(orderList, null);
            }
        });
    }
}
