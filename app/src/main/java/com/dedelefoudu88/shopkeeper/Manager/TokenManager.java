package com.dedelefoudu88.shopkeeper.Manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tony on 2017/3/22.
 */

public class TokenManager {

    private static final String TOKEN_KEY = "KEY";

    private static TokenManager sManager;
    private static Context sContext;
    private SharedPreferences mPref;
    private String mToken;

    public static synchronized void init(Context context){
        TokenManager.sContext = context;
    }

    private TokenManager(){
        mPref = sContext.getSharedPreferences("Token", Context.MODE_PRIVATE);
        mToken = mPref.getString(TOKEN_KEY, "");
    }

    public static synchronized TokenManager getInstance(){
        if (sManager == null){
            sManager = new TokenManager();
        }
        return sManager;
    }

    public String getToken(){
        return this.mToken;
    }

    public void setToken(String token){
        this.mToken = token == null ? "" : token;
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(TOKEN_KEY, this.mToken);
        editor.apply();
    }
}
