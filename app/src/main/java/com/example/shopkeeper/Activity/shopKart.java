package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shopkeeper.Adapter.ShopKartListViewAdaptateur;
import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.R;

import java.util.ArrayList;

public class shopKart extends AppCompatActivity {

    ArrayList<ItemMakeSellListVew> listP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_kart);

        Bundle bundle = getIntent().getExtras();
        this.listP = bundle.getParcelableArrayList("Kart list");

        ShopKartListViewAdaptateur listViewAdaptateur;
        //Création et initialisation de l'Adapter pour les personnes
        listViewAdaptateur = new ShopKartListViewAdaptateur(this.getApplicationContext(), listP,(TextView) findViewById(R.id.FinalPrice2),(TextView) findViewById(R.id.FinalPriceHt2));

        //Récupération du composant ListView
        ListView listView = (ListView)findViewById(R.id.SellList2);

        //Initialisation de la liste avec les données
        listView.setAdapter(listViewAdaptateur);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),CatalogMakeSaleMain.class);
        Bundle bundle = new Bundle();
        bundle.putInt("parentId", 0);
        bundle.putParcelableArrayList("Kart list",listP);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
        finish();
    }
}
