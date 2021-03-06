package com.dedelefoudu88.shopkeeper.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dedelefoudu88.shopkeeper.R;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String CoName = preferences.getString("CoName", "");
        setTitle(CoName);
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
