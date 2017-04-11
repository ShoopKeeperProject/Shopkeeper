package com.example.shopkeeper.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.shopkeeper.R;

public class SettingActivity extends AppCompatActivity {

    EditText mCoName;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mCoName = (EditText) findViewById(R.id.name_change);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String CoName = preferences.getString("CoName", "");
        setTitle(CoName);
    }

    public void mClick(View v) {
        //Log.v("EditText", mCoName.getText().toString());
        setTitle(mCoName.getText().toString());
        editor = preferences.edit();
        editor.putString("CoName", mCoName.getText().toString());
        editor.apply();
    }

}

