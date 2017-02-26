package com.example.shopkeeper.Manager;

import android.os.Handler;
import android.os.Looper;

import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.Model.User;

import java.util.ArrayList;

/**
 * Created by Tony on 2017/2/26.
 */

public class UserManager {

    private Handler mHandler;
    private static UserManager sManager;

    private UserManager(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    public synchronized static UserManager getInstance(){
        if (sManager == null){
            sManager = new UserManager();
        }
        return sManager;
    }

    public void signUp(String name, String password, final CallBack<Void> callBack){
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

    public void login(String name, String password, final CallBack<User> callBack){
        if (callBack == null){
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                User user = new User("David");
                callBack.onResponse(user, null);
            }
        });
    }

    public void getCurrentUser(final CallBack<User> callBack){
        if (callBack == null){
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                User user = new User("David");
                callBack.onResponse(user, null);
            }
        });
    }

    public void logout(final CallBack<Void> callBack){
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
