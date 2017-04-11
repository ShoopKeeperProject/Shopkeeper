package com.example.shopkeeper.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopkeeper.Adapter.MakeSaleListViewAdaptateur;
import com.example.shopkeeper.Adapter.MakeSaleResiclerViewAdaptateur;
import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.ProductManager;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.R;

import java.util.ArrayList;
import java.util.List;


public class CatalogMakeSaleMain extends AppCompatActivity {


    String parentId;

    ArrayList<ItemMakeSellListVew> listP;
    private MakeSaleResiclerViewAdaptateur mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_make_sale_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);

        Bundle bundle = getIntent().getExtras();
        this.parentId = bundle.getString("parentId");
        if (savedInstanceState == null) {
            this.listP = bundle.getParcelableArrayList("Kart list");
        } else { // savedInstanceState has saved values
            this.listP = savedInstanceState.getParcelableArrayList("Kart list3");
        }

        // editing resicler view, item

        RecyclerView mResiclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mResiclerView.setItemAnimator(new DefaultItemAnimator());
        mResiclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));



        // get item from this id
        ArrayList<Category> items = new ArrayList<>();

        // editing list view, shoping kart

        final MakeSaleListViewAdaptateur listViewAdaptateur;
        //Création et initialisation de l'Adapter pour les personnes
        listViewAdaptateur = new MakeSaleListViewAdaptateur(this.getApplicationContext(), listP,(TextView) findViewById(R.id.FinalPrice));

        //Récupération du composant ListView
        ListView listView = (ListView)findViewById(R.id.SellList);

        //Initialisation de la liste avec les données
        listView.setAdapter(listViewAdaptateur);

        /*ProductRepository rep = new ProductRepository();

          rep.get(parentId, CallBack< List<Category> > items) => please help*/

        /*
        items.add(new Category("2", parentId, "Alchool "+parentId, ""));
        items.add(new Category("3", parentId, "cat2 "+parentId, ""));
        items.add(new Product("4", parentId, "Wine "+parentId,10.2,20, "", "Red wine"));
        items.add(new Product("5", parentId, "Beer "+parentId,1.3,20, "", "ABC brand"));
        items.add(new Product("6", parentId, "Hello "+parentId,4,20, "", "Testing product"));
        items.add(new Product("1", parentId, this.getString(R.string.Others),0,20, "", "Testing product"));

        Product productTest = new Product("5", parentId, "test description "+parentId,3,20, "");
        productTest.AddProductDescription(new ProductDescription("Le produit qui dechire","pour celement \n\t 1 oui celemenet 1 miliar de fucking euro\n\tun razour!!"));
        productTest.AddProductDescription(new ProductDescription("carateristique","Composer d'un putain de lame tranchate,\n\tOui une seulle lame\nil vous permetra de tancher la gorge de vos enemy\ntest\n" +
                 "test\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\n" ));
        items.add(productTest);
        */

        mAdapter = new MakeSaleResiclerViewAdaptateur(this,items,listViewAdaptateur);
        mResiclerView.setAdapter(mAdapter);

        Button submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),shopKart.class);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("Kart list",listViewAdaptateur.items );
                intent.putExtras(bundle2);
                startActivityForResult(intent, 1);
            }
        });

        ProductManager.getInstance().getList(parentId, true, new CallBack<List<Category>>() {
            @Override
            public void onResponse(List<Category> result, ShooperKeeperException ex) {
                if (null != ex){
                    Toast.makeText(CatalogMakeSaleMain.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                mAdapter.addAll(result);
            }
        });
        mAdapter.add(new Product("1", parentId, this.getString(R.string.Others),0,20, "", ""));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);
    }

    @Override
    public void onBackPressed()
    {

        if (parentId == null || parentId.isEmpty())
        {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.Warning)
                    .setMessage(R.string.WarningMessageBack)
                    .setPositiveButton(R.string.Ok,
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialoginterface, int i)
                                {
                                    CatalogMakeSaleMain.super.onBackPressed();
                                }
                            })
                    .setNegativeButton(R.string.Cancel,
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialoginterface, int i)
                                {

                                }
                            })
                    .show();
        }
        else
        {
            /*
            Intent intent = new Intent(getApplicationContext(),CatalogMakeSaleMain.class);
            Bundle bundle = new Bundle();
            bundle.putString("parentId", "");
            bundle.putParcelableArrayList("Kart list",listP);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
            */
            Intent intent = new Intent();
            intent.putExtra("parentId", "");
            intent.putParcelableArrayListExtra("Kart list",listP);
            super.onBackPressed();
        }

        /*
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("Kart list2", this.listP);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
        finish();*/
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList("Kart list3", listP);
        super.onSaveInstanceState(savedInstanceState);
    }
    //onRestoreInstanceState
    /*
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        listP = savedInstanceState.getParcelableArrayList("Kart list3");
    }*/
}
