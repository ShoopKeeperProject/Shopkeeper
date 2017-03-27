package com.example.shopkeeper.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopkeeper.R;

public class TopSeller extends AppCompatActivity {

    //for the databasSeller
    EditText userN;
    String id;
    TextView amounts, results;
    DatabaseSeller SellerDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_seller);

        //for the databaseSeller
        userN = (EditText) findViewById(R.id.userName);
        amounts = (TextView) findViewById(R.id.FinalPrice2);
        SellerDb = new DatabaseSeller(this);
        results = (TextView) findViewById(R.id.tv_results);

        Cursor res = SellerDb.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(this, "No record in the Seller Database." ,Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("userName :"+ res.getString(1)+"\n");
            buffer.append("amounts :"+ res.getString(2)+"\n");
        }
        results.setText(buffer.toString());
        //Double cnyRate = SellerDb.getDouble("CNY");
    }
}
