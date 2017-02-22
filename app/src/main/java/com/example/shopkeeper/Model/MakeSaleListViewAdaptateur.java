package com.example.shopkeeper.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopkeeper.R;

import java.util.List;

/**
 * Created by dedel on 22/02/2017.
 */

public class MakeSaleListViewAdaptateur extends BaseAdapter {

    private List<ItemMakeSellListVew> items;
    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    public MakeSaleListViewAdaptateur(Context context, List<ItemMakeSellListVew> aListP) {
        mContext = context;
        items = aListP;
        mInflater = LayoutInflater.from(mContext);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.item_sell_listview, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView textItemName = (TextView) layoutItem.findViewById(R.id.ItemNameList);
        TextView textPu = (TextView) layoutItem.findViewById(R.id.pu);
        TextView textPt = (TextView) layoutItem.findViewById(R.id.pt);
        EditText textqt = (EditText) layoutItem.findViewById(R.id.qt);

        //(3) : Renseignement des valeurs
        textItemName.setText(items.get(position).getName());
        textPu.setText(String.format("%.2f",items.get(position).getPu()));
        textqt.setText(String.format("%d",items.get(position).getQt()));
        textPt.setText(String.format("%.2f",items.get(position).getPu()*items.get(position).getQt()));

        //On retourne l'item créé.
        return layoutItem;
    }
/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_sell_listview, parent, false);

        TextView textItemName = (TextView) rowView.findViewById(ItemName);
        TextView textPu = (TextView) rowView.findViewById(R.id.pu);
        TextView textPt = (TextView) rowView.findViewById(R.id.pt);
        EditText textqt = (EditText) rowView.findViewById(R.id.qt);


        ItemName.setText(getItem(position));

        return rowView;
    }

    public MakeSaleListViewAdaptateur(Context context, String[] values) {
        super(context, R.layout.item_sell_listview, values);
    }
*/
}
