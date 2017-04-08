package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.shopkeeper.R;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }
    public void topseller(View v) {
        Intent intent = new Intent(getApplicationContext(), TopSeller.class);
        Bundle bundle = new Bundle();
        bundle.putString("parentId", "");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void topproduct(View v) {
        Intent intent = new Intent(getApplicationContext(), TopProduct.class);
        Bundle bundle = new Bundle();
        bundle.putString("parentId", "");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
