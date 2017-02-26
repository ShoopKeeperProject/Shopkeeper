package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shopkeeper.Adapter.MakeSaleListViewAdaptateur;
import com.example.shopkeeper.Adapter.MakeSaleResiclerViewAdaptateur;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.R;

import java.util.ArrayList;


public class CatalogMakeSaleMain extends AppCompatActivity {

    int parentId;

    ArrayList<ItemMakeSellListVew> listP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_make_sale_main);

        // editing resicler view, item

        RecyclerView mResiclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mResiclerView.setItemAnimator(new DefaultItemAnimator());
        mResiclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        // get parent id
        Bundle bundle = getIntent().getExtras();
        this.parentId = bundle.getInt("parentId");
        this.listP = bundle.getParcelableArrayList("Kart list");

        // get item from this id
        ArrayList<Category> items = new ArrayList<>();

        // editing list view, shoping kart

        MakeSaleListViewAdaptateur adapter;
        //Création et initialisation de l'Adapter pour les personnes
        adapter = new MakeSaleListViewAdaptateur(this.getApplicationContext(), listP,(TextView) findViewById(R.id.FinalPrice));

        //Récupération du composant ListView
        ListView listView = (ListView)findViewById(R.id.SellList);

        //Initialisation de la liste avec les données
        listView.setAdapter(adapter);

        /*ProductRepository rep = new ProductRepository();

          rep.get(parentId, CallBack< List<Category> > items) => please help*/

        items.add(new Category(1, parentId, "Alchool "+parentId, ""));
        items.add(new Product(2, parentId, "Wine "+parentId,10.1, "", "Red wine"));
        items.add(new Product(3, parentId, "Beer "+parentId,1.2, "", "ABC brand"));
        items.add(new Product(4, parentId, "Hello "+parentId,3, "", "Testing product"));



        mResiclerView.setAdapter(new MakeSaleResiclerViewAdaptateur(this,items,adapter));




    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("Kart list2", this.listP);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                listP = intent.getParcelableArrayListExtra("Kart list2");
            }
        }
    }
}
