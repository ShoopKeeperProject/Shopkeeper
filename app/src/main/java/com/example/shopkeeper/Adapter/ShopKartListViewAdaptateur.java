package com.example.shopkeeper.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.R;

import java.util.ArrayList;

/**
 * Created by dedel on 27/02/2017.
 */

public class ShopKartListViewAdaptateur extends BaseAdapter {

    public ArrayList<ItemMakeSellListVew> items;
    //Le contexte dans lequel est présent notre adapter
    private Context mContext;
    private LinearLayout layoutItem;
    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;
    private final TextView mtotal;
    private final TextView mtotalht;
    double dTotal;
    double dtotalht;

    public ShopKartListViewAdaptateur(Context context, ArrayList<ItemMakeSellListVew> aListP, TextView total, TextView mtotalht) {
        this.mContext = context;
        this.items = aListP;
        this.mInflater = LayoutInflater.from(mContext);
        this.mtotal = total;
        this.mtotalht = mtotalht;
        dTotal = 0;
        dtotalht = 0;
        for (int i = 0; i < items.size(); i++) {
            dTotal += items.get(i).getPu() * items.get(i).getQt();
            dtotalht += items.get(i).getPu() * items.get(i).getQt() * (100 - items.get(i).getTaxe())/100;
        }
        mtotal.setText(String.format("%.2f", dTotal));
        mtotalht.setText(String.format("%.2f", dtotalht));
    }


        @Override
        public void notifyDataSetChanged()
        {
            super.notifyDataSetChanged();
            dTotal = 0;
            dtotalht = 0;
            for (int i = 0; i < items.size(); i++) {
                dTotal += items.get(i).getPu() * items.get(i).getQt();
                dtotalht += items.get(i).getPu() * items.get(i).getQt() * (100 - items.get(i).getTaxe())/100;
            }
            mtotal.setText(String.format("%.2f", dTotal));
            mtotalht.setText(String.format("%.2f", dtotalht));
        }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.item_sell_listview2, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView textItemName = (TextView) layoutItem.findViewById(R.id.ItemNameList);
        TextView textPu = (TextView) layoutItem.findViewById(R.id.pu);
        final TextView textPt = (TextView) layoutItem.findViewById(R.id.pt);
        final EditText textqt = (EditText) layoutItem.findViewById(R.id.qt);

        //(3) : Renseignement des valeurs
        textItemName.setText(items.get(position).getName());
        textPu.setText(String.format("%.2f", items.get(position).getPu()));
        textqt.setText(String.format("%d", items.get(position).getQt()));
        textPt.setText(String.format("%.2f", items.get(position).getPu() * items.get(position).getQt()));

        Button reduceQt = (Button) layoutItem.findViewById(R.id.ReduceQt);
        Button increaseQt = (Button) layoutItem.findViewById(R.id.IncreaseQt);
        reduceQt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Perform action on clicks

                int qt = items.get(position).getQt();
                qt--;
                dTotal -= items.get(position).getPu();
                //mtotal.setText(String.format("%.2f", dTotal));
                dtotalht -= items.get(position).getPu() * (100 - items.get(position).getTaxe())/100;
                //mtotalht.setText(String.format("%.2f", dtotalht));
                if (qt == 0) {
                    items.remove(position);
                    notifyDataSetChanged();
                } else {
                    items.get(position).setQt(qt);
                    //notifyDataSetChanged();
                    textqt.setText(String.format("%d", items.get(position).getQt()));
                }

            }
        });

        increaseQt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qt = items.get(position).getQt();
                qt++;
                items.get(position).setQt(qt);

                dTotal += items.get(position).getPu();
                //mtotal.setText(String.format("%.2f", dTotal));
                dtotalht += items.get(position).getPu() * (100 - items.get(position).getTaxe())/100;
                //mtotalht.setText(String.format("%.2f", dtotalht));
                //notifyDataSetChanged();
                textqt.setText(String.format("%d", items.get(position).getQt()));
            }
        });

        textqt.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                if(!textqt.getText().toString().isEmpty()) {
                    int qt = Integer.parseInt(textqt.getText().toString());
                    if (qt < 0)
                    {
                        items.remove(position);
                        notifyDataSetChanged();
                    }
                    else
                    {
                        items.get(position).setQt(qt);
                        dTotal = 0;
                        dtotalht = 0;
                        for (int i = 0; i < items.size(); i++) {
                            dTotal += items.get(i).getPu() * items.get(i).getQt();
                            dtotalht += items.get(i).getPu() * items.get(i).getQt() * (100 - items.get(i).getTaxe()) / 100;
                        }
                        textPt.setText(String.format("%.2f", items.get(position).getPu() * items.get(position).getQt()));
                        mtotal.setText(String.format("%.2f", dTotal));
                        mtotalht.setText(String.format("%.2f", dtotalht));
                        //notifyDataSetChanged();
                    }
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        //On retourne l'item créé.
        return layoutItem;
    }

}