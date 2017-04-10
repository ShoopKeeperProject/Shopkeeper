package com.example.shopkeeper.Manager;

import com.android.volley.VolleyError;

/**
 * Created by Tony on 2017/3/4.
 */

public class ShooperKeeperException extends Exception {

    private Exception mException;
    private String msg;

    public ShooperKeeperException(Exception exception){
        this.mException = exception;
        this.msg = exception.getMessage();

        if (VolleyError.class.isAssignableFrom(exception.getClass())){
            VolleyError ve = (VolleyError)exception;
            String mMsg = ve.networkResponse.toString() != null ? new String(ve.networkResponse.toString()) : null;
            if (mMsg != null){
                this.msg = mMsg;
            }
        }
    }

    public ShooperKeeperException(String msg){
        this.msg = msg;
    }

    public void printStackTrace() {
        if (mException != null){
            mException.printStackTrace();
        } else {
            super.printStackTrace();
        }
    }

    @Override
    public String getMessage(){
        if (mException != null){
            mException.printStackTrace();
            return mException.getMessage();
        } else {
            return msg;
        }
    }
}
