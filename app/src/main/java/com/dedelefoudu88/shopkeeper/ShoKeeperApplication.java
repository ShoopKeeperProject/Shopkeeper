package com.dedelefoudu88.shopkeeper;

import android.app.Application;
import android.content.Context;

import com.dedelefoudu88.shopkeeper.Manager.OrderHistoryManager;
import com.dedelefoudu88.shopkeeper.Manager.OrderManager;
import com.dedelefoudu88.shopkeeper.Manager.ProductManager;
import com.dedelefoudu88.shopkeeper.Manager.ShopManager;
import com.dedelefoudu88.shopkeeper.Manager.TokenManager;
import com.dedelefoudu88.shopkeeper.Manager.UserManager;

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
