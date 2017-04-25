package com.dedelefoudu88.shopkeeper.Manager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dedelefoudu88.shopkeeper.Model.Order;
import com.dedelefoudu88.shopkeeper.R;
import com.dedelefoudu88.shopkeeper.Utilities.NetworkUtil;
import com.dedelefoudu88.shopkeeper.Utilities.ShopKeeperJsonAuthRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tony on 2017/3/22.
 */

public class OrderManager {

    private static OrderManager sManager;
    private static Context sContext;

    public static void init(Context context){
        OrderManager.sContext = context;
    }

    private OrderManager(){
    }

    public synchronized static OrderManager getInstance(){
        if (sManager == null){
            sManager = new OrderManager();
        }
        return sManager;
    }

    public void addOrders(Order[] orders, final CallBack<Void> callBack){
        if (callBack == null){
            return;
        }

        JSONObject para = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            for (Order order: orders){
                JSONObject subOrder = new JSONObject();
                subOrder.put("amount", order.getAmount());
                subOrder.put("price", (int)(order.getPrice() * 100));
                subOrder.put("productId", order.getProductId());
                subOrder.put("amount", order.getAmount());
                jsonArray.put(subOrder);
            }
            para.put("orders", jsonArray);
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new ShopKeeperJsonAuthRequest
                (Request.Method.POST, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_order), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (!response.isNull("result")){
                            callBack.onResponse(null, null);
                        } else {
                            callBack.onResponse(null, new ShooperKeeperException("System Error"));
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
