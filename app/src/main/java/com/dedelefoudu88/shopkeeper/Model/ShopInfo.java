package com.dedelefoudu88.shopkeeper.Model;

import com.dedelefoudu88.shopkeeper.Utilities.JSONUtili;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tony on 2017/3/22.
 */

public class ShopInfo {
    private String mId;
    private String mName;
    private String mIconUrl;

    public ShopInfo(JSONObject obj) throws JSONException {
        this(JSONUtili.getString(obj, "id"), JSONUtili.getString(obj, "name"), JSONUtili.getString(obj, "iconUrl"));
    }

    public ShopInfo(String id, String name, String iconUrl){
        this.mId = id;
        this.mName = name;
        this.mIconUrl = iconUrl;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String mIconUrl) {
        this.mIconUrl = mIconUrl;
    }
}
