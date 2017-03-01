package com.example.shopkeeper.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shopkeeper.Adapter.MakeSaleListViewAdaptateur;
import com.example.shopkeeper.Adapter.MakeSaleResiclerViewAdaptateur;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.Model.ProductDescription;
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

        final MakeSaleListViewAdaptateur listViewAdaptateur;
        //Création et initialisation de l'Adapter pour les personnes
        listViewAdaptateur = new MakeSaleListViewAdaptateur(this.getApplicationContext(), listP,(TextView) findViewById(R.id.FinalPrice));

        //Récupération du composant ListView
        ListView listView = (ListView)findViewById(R.id.SellList);

        //Initialisation de la liste avec les données
        listView.setAdapter(listViewAdaptateur);

        /*ProductRepository rep = new ProductRepository();

          rep.get(parentId, CallBack< List<Category> > items) => please help*/

        items.add(new Category(1, parentId, "Alchool "+parentId, ""));
        items.add(new Product(2, parentId, "Wine "+parentId,10.1,20, "", "Red wine"));
        items.add(new Product(3, parentId, "Beer "+parentId,1.2,20, "", "ABC brand"));
        items.add(new Product(4, parentId, "Hello "+parentId,3,20, "", "Testing product"));

        Product productTest = new Product(5, parentId, "test description "+parentId,3,20, "");
        productTest.AddProductDescription(new ProductDescription("Le produit qui dechire","pour celement \n\t 1 oui celemenet 1 miliar de fucking euro\n\tun razour!!"));
        productTest.AddProductDescription(new ProductDescription("carateristique","Composer d'un putain de lame tranchate,\n\tOui une seulle lame\nil vous permetra de tancher la gorge de vos enemy\ntest\n" +
                 "test\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\n" ));
        items.add(productTest);

        mResiclerView.setAdapter(new MakeSaleResiclerViewAdaptateur(this,items,listViewAdaptateur));

        Button submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),shopKart.class);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("Kart list",listViewAdaptateur.items );
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        if (parentId == 0)
        {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.Warning)
                    .setMessage(R.string.WarningMessageBack)
                    .setPositiveButton(R.string.Ok,
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialoginterface, int i)
                                {
                                    Intent intent = new Intent(getApplicationContext(),MainSeller.class);
                                    startActivity(intent);
                                    finish();
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
            Intent intent = new Intent(getApplicationContext(),CatalogMakeSaleMain.class);
            Bundle bundle = new Bundle();
            bundle.putInt("parentId", 0);
            bundle.putParcelableArrayList("Kart list",listP);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

        //super.onBackPressed();
        /*Intent intent = new Intent();
        intent.putParcelableArrayListExtra("Kart list2", this.listP);
        setResult(RESULT_OK, intent);
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
}
