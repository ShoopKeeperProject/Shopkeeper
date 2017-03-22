package com.example.shopkeeper;

import android.app.Application;
import android.content.Context;

import com.example.shopkeeper.Manager.OrderHistoryManager;
import com.example.shopkeeper.Manager.OrderManager;
import com.example.shopkeeper.Manager.ProductManager;
import com.example.shopkeeper.Manager.ShopManager;
import com.example.shopkeeper.Manager.TokenManager;
import com.example.shopkeeper.Manager.UserManager;

/**
 * Created by Tony on 2017/3/4.
 */

public class ShoKeeperApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Context context = getApplicationContext();
        UserManager.init(context);
        ProductManager.init(context);
        OrderManager.init(context);
        OrderHistoryManager.init(context);
        ShopManager.init(context);
        TokenManager.init(context);
    }
}
