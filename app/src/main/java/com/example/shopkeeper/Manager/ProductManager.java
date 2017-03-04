package com.example.shopkeeper.Manager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.Model.ProductDescription;
import com.example.shopkeeper.R;
import com.example.shopkeeper.Utilities.MultiPartRequest;
import com.example.shopkeeper.Utilities.NetworkUtil;
import com.example.shopkeeper.Utilities.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Tony on 2017/2/18.
 */

public class ProductManager {

    private static ProductManager sProductRepo;
    private Handler mHandler;
    private static Context sContext;

    public static void init(Context context){
        ProductManager.sContext = context;
    }

    private ProductManager(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    public synchronized static ProductManager getInstance(){
        if (sProductRepo == null){
            sProductRepo = new ProductManager();
        }
        return sProductRepo;
    }

    public void getList(String parentId, final CallBack<List<Category>> callBack){
        if (callBack == null){
            return;
        }

        JSONObject para = new JSONObject();
        try {
            para.put("parentId", parentId == null ? "" : parentId);
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_get_products), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("result");
                            ArrayList<Category> arrayList = new ArrayList<>();

                            for (int i = 0 ; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                boolean isDirectory = obj.getBoolean("isDirectory");
                                Category category = null;
                                if (isDirectory){
                                    category = new Category(obj);
                                } else {
                                    category = new Product(obj);
                                }
                                arrayList.add(category);
                            }

                            callBack.onResponse(arrayList, null);
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

    public void get(String productId, final CallBack<Category> callBack){
        if (callBack == null){
            return;
        }

        JSONObject para = new JSONObject();
        try {
            para.put("id", productId);
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_get_producs), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("result");

                            boolean isDirectory = obj.getBoolean("isDirectory");
                            Category category = null;
                            if (isDirectory){
                                category = new Category(obj);
                            } else {
                                category = new Product(obj);
                            }

                            callBack.onResponse(category, null);
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

    public void createOrUpdateProduct(Product product, final CallBack<Product> callBack){
        if (callBack == null){
            return;
        }
        JSONObject para = new JSONObject();
        String url = null;
        try {
            if (Parser.isEmpty(product.getmId())) {
                para.put("id", product.getmId());
            }
            if (Parser.isEmpty(product.getmName())) {
                para.put("name", product.getmName());
            }
            para.put("price", Double.toString(product.getmPrice()));
            para.put("tax", Double.toString(product.getmTaxe()));
            if (Parser.isEmpty(product.getmImageURL())) {
                para.put("imageUrl", product.getmImageURL());
            }
            if (Parser.isEmpty(product.getmParentId())) {
                para.put("parentId", product.getmParentId());
            }

            int descriptionSize = product.GetProductDescriptionSize();
            if (descriptionSize > 0){
                JSONArray jsonArray = new JSONArray();
                for (int cnt = 0; cnt < descriptionSize; cnt++){
                    JSONObject jsonObject = new JSONObject();
                    ProductDescription description = product.getmDescription(cnt);
                    jsonObject.put("title", description.getMtitle());
                    jsonObject.put("description", description.getMdescription());
                    jsonArray.put(jsonObject);
                }
                para.put("productDescription", jsonArray);
            }
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_create_or_update_product), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("result");
                            callBack.onResponse(new Product(obj), null);
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

    public void createOrUpdateCategory(Category category, final CallBack<Category> callBack){
        if (callBack == null){
            return;
        }
        JSONObject para = new JSONObject();
        String url = null;
        try {
            if (Parser.isEmpty(category.getmId())) {
                para.put("id", category.getmId());
            }
            if (Parser.isEmpty(category.getmName())) {
                para.put("name", category.getmName());
            }
            if (Parser.isEmpty(category.getmImageURL())) {
                para.put("imageUrl", category.getmImageURL());
            }

            if (Parser.isEmpty(category.getmParentId())) {
                para.put("parentId", category.getmParentId());
            }
        } catch (JSONException ex){
            throw new RuntimeException(ex);
        }

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_create_or_update_category), para, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("result");
                            Category category = new Category(obj.getString("id"), obj.getString("parentId"), obj.getString("name"), obj.getString("imageUrl"));
                            callBack.onResponse(category, null);
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

    public void uploadImage(final Uri imageUrl, final CallBack<String> callBack){
        MultiPartRequest multipartRequest = new MultiPartRequest(Request.Method.POST, NetworkUtil.buildURL(sContext, R.string.server_base_url, R.string.path_upload_file), new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    JSONObject jsonObject = new JSONObject(new String(response.data));
                    String url = jsonObject.getString("result");
                    callBack.onResponse(url, null);
                } catch (JSONException ex){
                    callBack.onResponse(null, new ShooperKeeperException(ex));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onResponse(null, new ShooperKeeperException(error));
            }
        }) {
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                try {
                    InputStream iStream = sContext.getContentResolver().openInputStream(imageUrl);
                    ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
                    BufferedInputStream buf = new BufferedInputStream(iStream);
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];

                    int len = 0;
                    while ((len = buf.read(buffer)) != -1) {
                        byteBuffer.write(buffer, 0, len);
                    }
                    params.put("file", new DataPart("product.jpg", byteBuffer.toByteArray(), "image/jpeg"));
                    Log.d("Androidmutlipart", "data: " +byteBuffer.toByteArray().length);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return params;
            }
        };
        NetworkUtil.getInstance(sContext).getQueue().add(multipartRequest);
    }

    public void delete(List<Product> products, final CallBack<Void> callBack){
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
