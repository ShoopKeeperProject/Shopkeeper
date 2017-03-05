package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.R;

import java.util.ArrayList;

public class MainSeller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);
        final Button buttonMakeSell  = (Button) findViewById(R.id.makeSell);
        buttonMakeSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CatalogMakeSaleMain.class);
                Bundle bundle = new Bundle();
                bundle.putString("parentId", null);

                //
                //Récupération de la liste des personnes
                ArrayList<ItemMakeSellListVew> listP = new ArrayList<>();
                bundle.putParcelableArrayList("Kart list",listP);

                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });
    }


}
