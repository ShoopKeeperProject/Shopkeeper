package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopkeeper.Adapter.ShopKartListViewAdaptateur;
import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.OrderManager;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.Model.Order;
import com.example.shopkeeper.R;

import java.util.ArrayList;

public class shopKart extends AppCompatActivity {

    ArrayList<ItemMakeSellListVew> listP;
    private View mConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_kart);

        mConfirmBtn = findViewById(R.id.Submit);

        Bundle bundle = getIntent().getExtras();
        this.listP = bundle.getParcelableArrayList("Kart list");

        ShopKartListViewAdaptateur listViewAdaptateur;
        //Création et initialisation de l'Adapter pour les personnes
        listViewAdaptateur = new ShopKartListViewAdaptateur(this.getApplicationContext(), listP,(TextView) findViewById(R.id.FinalPrice2),(TextView) findViewById(R.id.FinalPriceHt2));

        //Récupération du composant ListView
        ListView listView = (ListView)findViewById(R.id.SellList2);

        //Initialisation de la liste avec les données
        listView.setAdapter(listViewAdaptateur);

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.Submit:{
                        ArrayList<Order> orders = new ArrayList<Order>();
                        for (ItemMakeSellListVew item: listP){
                            orders.add(new Order(item.getQt(), item.getPu(), item.getId()));
                        }
                        OrderManager.getInstance().addOrders(orders.toArray(new Order[0]), new CallBack<Void>() {
                            @Override
                            public void onResponse(Void result, ShooperKeeperException ex) {
                                if (ex != null){
                                    Toast.makeText(shopKart.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                                } else {
                                    finish();
                                }
                            }
                        });
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("Kart list2", this.listP);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
        finish();
    }
}
