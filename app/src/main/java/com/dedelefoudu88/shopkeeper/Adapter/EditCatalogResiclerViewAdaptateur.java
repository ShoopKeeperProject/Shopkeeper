package com.dedelefoudu88.shopkeeper.Adapter;

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
import com.dedelefoudu88.shopkeeper.Activity.EditCatalogMainActivity;
import com.dedelefoudu88.shopkeeper.Activity.EditableCategoryPage;
import com.dedelefoudu88.shopkeeper.Activity.EditableProductPage;
import com.dedelefoudu88.shopkeeper.Model.Category;
import com.dedelefoudu88.shopkeeper.Model.Product;
import com.dedelefoudu88.shopkeeper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedel on 22/02/2017.
 */

public class EditCatalogResiclerViewAdaptateur extends RecyclerView.Adapter<EditCatalogResiclerViewAdaptateur.ViewHolder>
{
    @SuppressWarnings("unused")
    //private static final String TAG = MakeSaleResiclerViewAdaptateur.class.getSimpleName();

/*
    public MakeSaleResiclerViewAdaptateur() {


        // Create some items
        items = new ArrayList<>();

    }
*/
    private ArrayList<Category> items;
    //Le contexte dans lequel est présent notre adapter
    private Activity mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    public EditCatalogResiclerViewAdaptateur(Activity context, ArrayList<Category> aListP) {
        super();
        this.mContext = context;
        this.items = aListP;
        this.mInflater = LayoutInflater.from(mContext);
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
                    Product item3 = (Product) item2;
                    Intent intent = new Intent(mContext,EditableProductPage.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("product", item3);
                    bundle.putInt("position", position);
                    intent.putExtras(bundle);
                    mContext.startActivityForResult(intent,4);
                }
                else
                {
                    Intent intent = new Intent(mContext,EditCatalogMainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("parentId", item2.getmId());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);

                }

            }
        });
        viewHolder.ItemImage.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Category item2 = items.get(position);

                if (!item2.isProduct())
                {

                    Intent intent = new Intent(mContext,EditableCategoryPage.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("category", item2);
                    bundle.putInt("position", position);
                    intent.putExtras(bundle);
                    mContext.startActivityForResult(intent,6);
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
        holder.ItemImage.setImageResource(R.drawable.icon_128);
        //holder.ItemImage.setScaleType(ImageView.ScaleType.FIT_XY);

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
