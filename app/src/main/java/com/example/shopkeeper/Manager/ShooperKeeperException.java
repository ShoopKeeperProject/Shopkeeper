package com.example.shopkeeper.Manager;

/**
 * Created by Tony on 2017/3/4.
 */

public class ShooperKeeperException extends Exception {

    private Exception mException;
    private String msg;

    public ShooperKeeperException(Exception exception){
        this.mException = exception;
        this.msg = exception.getMessage();
    }

    public ShooperKeeperException(String msg){
        this.msg = msg;
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
