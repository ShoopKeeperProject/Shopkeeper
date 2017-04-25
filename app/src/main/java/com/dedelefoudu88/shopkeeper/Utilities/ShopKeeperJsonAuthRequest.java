package com.dedelefoudu88.shopkeeper.Utilities;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dedelefoudu88.shopkeeper.Manager.TokenManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tony on 2017/3/22.
 */

public class ShopKeeperJsonAuthRequest extends JsonObjectRequest {

    public ShopKeeperJsonAuthRequest(int method, String url, JSONObject jsonRequest,
                             Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public ShopKeeperJsonAuthRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        TokenManager tokenManager = TokenManager.getInstance();
        String token = tokenManager.getToken();
        if (token != null && !token.isEmpty()){
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put("Authorization", "Bearer " + token);
            return authHeader;
        } else {
            return super.getHeaders();
        }
    }


}
