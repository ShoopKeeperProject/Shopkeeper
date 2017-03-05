package com.example.shopkeeper.Manager;

/**
 * Created by Tony on 2017/3/4.
 */

public class ShooperKeeperException extends Exception {

    private Exception mException;

    public ShooperKeeperException(Exception exception){
        this.mException = exception;
    }

    @Override
    public String getMessage(){
        mException.printStackTrace();
        return mException.getMessage();
    }
}
