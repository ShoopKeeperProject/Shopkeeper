package com.example.shopkeeper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ListView;

import com.example.shopkeeper.Methode.ItemMakeSellListVew;
import com.example.shopkeeper.Adapter.MakeSaleListViewAdaptateur;
import com.example.shopkeeper.Adapter.MakeSaleResiclerViewAdaptateur;
import com.example.shopkeeper.R;

import java.util.ArrayList;

import static com.example.shopkeeper.R.id.recycler_view;

public class CatalogMakeSaleMain extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_make_sale_main);

        RecyclerView mResiclerView = (RecyclerView) findViewById(recycler_view);
        mResiclerView.setItemAnimator(new DefaultItemAnimator());
        mResiclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        mResiclerView.setAdapter(new MakeSaleResiclerViewAdaptateur());

        //Récupération de la liste des personnes
        ArrayList<ItemMakeSellListVew> listP = new ArrayList<>();
        listP.add(new ItemMakeSellListVew("toto",2,10));
        listP.add(new ItemMakeSellListVew("titi",1,1.2));
        listP.add(new ItemMakeSellListVew("toto",2,10));
        listP.add(new ItemMakeSellListVew("titi",1,1.2));
        listP.add(new ItemMakeSellListVew("toto",2,10));
        listP.add(new ItemMakeSellListVew("titi",1,1.2));

        //Création et initialisation de l'Adapter pour les personnes
        MakeSaleListViewAdaptateur adapter = new MakeSaleListViewAdaptateur(this, listP);

        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.SellList);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

    }
}
