package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shopkeeper.R;

public class MainActivity extends AppCompatActivity {
    EditText mCoName;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.seller);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                Bundle bundle = new Bundle();
                bundle.putString("seller/customer", "seller");
                startActivity(intent);
            }
        });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);
    }
}
