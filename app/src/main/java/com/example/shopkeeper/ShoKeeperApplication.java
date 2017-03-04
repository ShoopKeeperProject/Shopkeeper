package com.example.shopkeeper;

import android.app.Application;

import com.example.shopkeeper.Manager.ProductManager;
import com.example.shopkeeper.Manager.UserManager;

/**
 * Created by Tony on 2017/3/4.
 */

public class ShoKeeperApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        UserManager.init(this.getApplicationContext());
        ProductManager.init(this.getApplicationContext());
    }
}
