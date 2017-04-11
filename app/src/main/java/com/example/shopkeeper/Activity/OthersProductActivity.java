package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.R;

import java.util.ArrayList;

public class OthersProductActivity extends AppCompatActivity {

    ArrayList<ItemMakeSellListVew> listP;
    String name;
    double pu;
    double taxe;
    EditText ETname;
    EditText ETpu;
    EditText ETtaxe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_product);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String CoName = preferences.getString("CoName", "");
        setTitle(CoName);

        taxe = 0;
        pu = 0;
        Bundle bundle = getIntent().getExtras();
        this.listP = bundle.getParcelableArrayList("Kart list");

        ETname = (EditText) findViewById(R.id.ItemName);
        ETpu = (EditText) findViewById(R.id.productPrice);
        ETtaxe = (EditText) findViewById(R.id.productTaxe);


        Button submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ETtaxe.getText().toString().isEmpty()) {
                    taxe = Double.parseDouble(ETtaxe.getText().toString());
                    if(!ETpu.getText().toString().isEmpty()) {
                        pu = Double.parseDouble(ETpu.getText().toString());
                        if(!ETname.getText().toString().isEmpty()) {
                            name = ETname.getText().toString();
                        }
                        else
                        {
                            name = "default";
                        }
                        listP.add(new ItemMakeSellListVew(null, name,1,pu,taxe));
                    }
                }
                Intent intent = new Intent(getApplicationContext(),CatalogMakeSaleMain.class);
                Bundle bundle = new Bundle();
                bundle.putString("parentId", "");
                bundle.putParcelableArrayList("Kart list",listP);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
