package com.dedelefoudu88.shopkeeper.Manager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dedelefoudu88.shopkeeper.Model.ProductHistoryRecord;
import com.dedelefoudu88.shopkeeper.Model.SellerHistoryRecord;
import com.dedelefoudu88.shopkeeper.R;
import com.dedelefoudu88.shopkeeper.Utilities.NetworkUtil;
import com.dedelefoudu88.shopkeeper.Utilities.ShopKeeperJsonAuthRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2017/2/26.
 */

public class OrderHistoryManager {

    private static OrderHistoryManager sManager;
    private static Context sContext;

    public static void init(Context context){
        OrderHistoryManager.sContext = context;
    }

    private OrderHistoryManager(){
    }

    public synchronized static OrderHistoryManager getInstance(){
        if (sManager == null){
            sManager = new OrderHistoryManager();
        }
        return sManager;
    }

    public void getTopTenProduct(long fromTime, final CallBack<List<ProductHistoryRecord>> callBack){
        if (callBack == null){
            return;
        }

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.GET, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_get_top_ten_products) + "?startTime=" + Long.toString(fromTime), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray objs = response.getJSONArray("result");
                            ArrayList<ProductHistoryRecord> record = new ArrayList<>();

                            for (int cnt = 0; cnt < objs.length(); cnt++){
                                record.add(new ProductHistoryRecord(objs.getJSONObject(cnt)));
                            }

                            callBack.onResponse(record, null);
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

    public void getSellerHistory(long fromTime, final CallBack<List<SellerHistoryRecord>> callBack){
        if (callBack == null){
            return;
        }

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.GET, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_get_staff_stat) + "?startTime=" + Long.toString(fromTime), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray objs = response.getJSONArray("result");
                            ArrayList<SellerHistoryRecord> record = new ArrayList<>();

                            for (int cnt = 0; cnt < objs.length(); cnt++){
                                record.add(new SellerHistoryRecord(objs.getJSONObject(cnt)));
                            }

                            callBack.onResponse(record, null);
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
