package com.example.shopkeeper.Manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.Model.ShopInfo;
import com.example.shopkeeper.R;
import com.example.shopkeeper.Utilities.NetworkUtil;
import com.example.shopkeeper.Utilities.ShopKeeperJsonAuthRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tony on 2017/3/22.
 */

public class ShopManager {

    private static ShopManager mManager;
    private static Context sContext;

    public static void init(Context context){
        ShopManager.sContext = context;
    }

    private ShopManager(){

    }

    public synchronized static ShopManager getInstance(){
        if (mManager == null){
            mManager = new ShopManager();
        }
        return mManager;
    }

    public void get(String shopId, final CallBack<ShopInfo> callBack){
        if (callBack == null){
            return;
        }

        JSONObject para = new JSONObject();
        try {
            para.put("id", shopId);
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.GET, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_shop_info), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("result");
                            ShopInfo shopInfo = new ShopInfo(obj);
                            callBack.onResponse(shopInfo, null);
                        } catch (JSONException ex){
                            callBack.onResponse(null, new ShooperKeeperException(ex));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onResponse(null, new ShooperKeeperException(error));
                    }
                });
        NetworkUtil.getInstance(sContext).getQueue().add(request);
    }

    public void set(ShopInfo shopInfo, final CallBack<ShopInfo> callBack){
        if (callBack == null){
            return;
        }

        JSONObject para = new JSONObject();
        try {
            para.put("id", shopInfo.getId());
            para.put("name", shopInfo.getName());
            para.put("iconUrl", shopInfo.getIconUrl());
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.POST, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_shop_info), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("result");
                            ShopInfo shopInfo = new ShopInfo(obj);
                            callBack.onResponse(shopInfo, null);
                        } catch (JSONException ex){
                            callBack.onResponse(null, new ShooperKeeperException(ex));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onResponse(null, new ShooperKeeperException(error));
                    }
                });
        NetworkUtil.getInstance(sContext).getQueue().add(request);
    }
}
