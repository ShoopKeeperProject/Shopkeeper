package com.dedelefoudu88.shopkeeper.Manager;

/**
 * Created by Tony on 2017/2/18.
 */

public interface CallBack <T> {
    public void onResponse(T result, ShooperKeeperException ex);
}
