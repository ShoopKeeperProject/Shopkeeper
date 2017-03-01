package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shopkeeper.R;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        this.finish();
    }

}
