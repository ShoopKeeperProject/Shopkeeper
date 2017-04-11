package com.example.shopkeeper.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkeeper.Activity.CatalogMakeSaleMain;
import com.example.shopkeeper.Activity.OthersProductActivity;
import com.example.shopkeeper.Activity.ProductPage;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.ItemMakeSellListVew;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedel on 22/02/2017.
 */

public class MakeSaleResiclerViewAdaptateur extends RecyclerView.Adapter<MakeSaleResiclerViewAdaptateur.ViewHolder>
{
    @SuppressWarnings("unused")
    //private static final String TAG = MakeSaleResiclerViewAdaptateur.class.getSimpleName();

/*
    public MakeSaleResiclerViewAdaptateur() {


        // Create some items
        items = new ArrayList<>();

    }
*/
    MakeSaleListViewAdaptateur listViewAdaptateur;
    private ArrayList<Category> items;
    //Le contexte dans lequel est présent notre adapter
    private Activity mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    public MakeSaleResiclerViewAdaptateur(Activity context, ArrayList<Category> aListP, MakeSaleListViewAdaptateur listViewAdaptateur) {
        super();
        this.mContext = context;
        this.items = aListP;
        this.mInflater = LayoutInflater.from(mContext);
        this.listViewAdaptateur = listViewAdaptateur;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sell_catalog_grid, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        viewHolder.ItemImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Category item2 = items.get(position);
                if (item2.isProduct())
                {
                    if ( item2.getmName().equals(mContext.getString(R.string.Others)) )
                    {
                        Intent intent = new Intent(mContext,OthersProductActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("Kart list",listViewAdaptateur.items);
                        intent.putExtras(bundle);
                        mContext.startActivityForResult(intent, 1);
                        //mContext.startActivity(intent);
                    }
                    else
                    {
                        Product item3 = (Product) item2;
                        listViewAdaptateur.add(new ItemMakeSellListVew(item3.getmId(), item3.getmName(), 1, item3.getmPrice(), item3.getmTaxe()));
                    }
                }
                else
                {
                    Intent intent = new Intent(mContext,CatalogMakeSaleMain.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("parentId", item2.getmId());
                    bundle.putParcelableArrayList("Kart list",listViewAdaptateur.items);
                    intent.putExtras(bundle);
                        mContext.startActivityForResult(intent, 10);
                    //mContext.startActivity(intent);

                }

            }
        });
        viewHolder.ItemImage.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Category item2 = items.get(position);

                if (item2.isProduct() && !item2.getmName().equals(mContext.getString(R.string.Others)))
                {
                    Product item3 = (Product) item2;
                    Intent intent = new Intent(mContext,ProductPage.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("product", item3);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);

                }
                return true;
            }
        });

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category item = items.get(position);

        if (item.isProduct())
        {
            View view = holder.itemView;
            view.setBackgroundColor(0xd8d8d8);
        }
        holder.ItemName.setText(item.getmName());
        if (mContext != null && item.getmImageURL() != null && !item.getmImageURL().isEmpty()) {
            Glide.with(mContext.getApplicationContext())
                    .load(item.getmImageURL())
                    .into(holder.ItemImage);
        } else {
            holder.ItemImage.setImageResource(R.drawable.icon_128);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(Category item){
        if (item.isEnable()) {
            items.add(item);
            notifyItemInserted(items.size() - 1);
        }
    }

    public void addAll(List<Category> categories){

        int length = categories.size();
        for (int i = 0; i< length; i++)
        {
            add(categories.get(i));
        }
        //items.addAll(categories);
        //notifyItemRangeInserted(length, categories.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ItemName;
        ImageView ItemImage;

        ViewHolder(View itemView) {
            super(itemView);

            ItemName = (TextView) itemView.findViewById(R.id.ItemName);
            ItemImage = (ImageView) itemView.findViewById(R.id.ItemImage);


        }
    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

}
