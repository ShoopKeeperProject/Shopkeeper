package com.example.shopkeeper.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopkeeper.Model.Category;
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
    private static final String TAG = MakeSaleResiclerViewAdaptateur.class.getSimpleName();

    private List<Category> items;

    public MakeSaleResiclerViewAdaptateur() {
        super();

        // Create some items
        items = new ArrayList<>();
        items.add(new Category("Cat1"));
        items.add(new Category("Cat2"));
        items.add(new Product("Product1"));
        items.add(new Category("Cat3"));
        items.add(new Category("Product2"));
        items.add(new Category("Product3"));
        items.add(new Category("Cat3"));
        items.add(new Category("Product4"));
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sell_catalog_grid, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category item = items.get(position);

        holder.ItemName.setText(item.getmName());
        holder.ItemImage.setImageResource(R.drawable.icon_128);


    }

    @Override
    public int getItemCount() {
        return items.size();
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
}
