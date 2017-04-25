package com.dedelefoudu88.shopkeeper.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dedelefoudu88.shopkeeper.Model.ItemMakeSellListVew;
import com.dedelefoudu88.shopkeeper.R;

import java.util.ArrayList;

public class MainSeller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);

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
                //startActivityForResult(intent, 1);
                startActivity(intent);
            }
        });
        final Button buttonEditCatalog  = (Button) findViewById(R.id.editCatalog);
        buttonEditCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditCatalogMainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("parentId", "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);
    }

    //call for the Statistics page
    public void makeReport(View v) {
        Intent intent = new Intent(getApplicationContext(), Statistics.class);
        Bundle bundle = new Bundle();
        bundle.putString("parentId", "");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void setting(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("parentId", "");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
