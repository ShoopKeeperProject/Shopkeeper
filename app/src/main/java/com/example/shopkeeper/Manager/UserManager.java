package com.example.shopkeeper.Manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.shopkeeper.Model.User;
import com.example.shopkeeper.R;
import com.example.shopkeeper.Utilities.JSONUtili;
import com.example.shopkeeper.Utilities.NetworkUtil;
import com.example.shopkeeper.Utilities.Parser;
import com.example.shopkeeper.Utilities.ShopKeeperJsonAuthRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tony on 2017/2/26.
 */

public class UserManager {

    private static UserManager sManager;
    private static Context sContext;
    private TokenManager mTokenMgr;

    public static void init(Context context){
        UserManager.sContext = context;
    }

    private UserManager(){
        mTokenMgr = TokenManager.getInstance();
    }

    public synchronized static UserManager getInstance(){
        if (sManager == null){
            sManager = new UserManager();
        }
        return sManager;
    }

    public void signUp(String email, String password, final CallBack<Void> callBack){
        if (callBack == null){
            return;
        }

        JSONObject para = new JSONObject();
        try {
            para.put("email", email);
            para.put("password", password);
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.POST, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_signup), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBack.onResponse(null, null);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onResponse(null, new ShooperKeeperException(error));
                    }
                });
        NetworkUtil.getInstance(sContext).getQueue().add(request);
    }

    public void login(String email, String password, final CallBack<User> callBack){
        if (callBack == null){
            return;
        }

        JSONObject para = new JSONObject();
        try {
            para.put("email", email);
            para.put("password", password);
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.POST, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_login), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("result");
                            String token = JSONUtili.getString(jsonObject, "token");
                            mTokenMgr.setToken(token);
                            callBack.onResponse(new User(jsonObject), null);
                        } catch (JSONException ex){
                            callBack.onResponse(null, new ShooperKeeperException(ex));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError ex) {
                        callBack.onResponse(null, new ShooperKeeperException(ex));
                    }
                });
        NetworkUtil.getInstance(sContext).getQueue().add(request);
    }

    public void logout(final CallBack<Void> callBack){
        if (callBack == null){
            return;
        }

        mTokenMgr.setToken(null);

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.POST, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_logout), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("result");
                            mTokenMgr.setToken(null);
                            callBack.onResponse(null, null);
                        } catch (JSONException ex){
                            callBack.onResponse(null, new ShooperKeeperException(ex));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError ex) {
                        callBack.onResponse(null, new ShooperKeeperException(ex));
                    }
                });
        NetworkUtil.getInstance(sContext).getQueue().add(request);
    }
}
