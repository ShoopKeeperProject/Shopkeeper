package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.OrderHistoryManager;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.ProductHistoryRecord;
import com.example.shopkeeper.Model.SellerHistoryRecord;
import com.example.shopkeeper.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    }
}
