package com.dedelefoudu88.shopkeeper.Utilities;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Tony on 2017/3/4.
 */

public class NetworkUtil {
    private static NetworkUtil sQueue;
    private RequestQueue queue;

    private NetworkUtil(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public synchronized static NetworkUtil getInstance(Context context){
        if (null == sQueue){
            sQueue = new NetworkUtil(context);
        }
        return sQueue;
    }

    public RequestQueue getQueue(){
        return queue;
    }

    public static String buildURL(Context context, int baseUrl, int path){
        return context.getString(baseUrl) + context.getString(path);
    }
}
