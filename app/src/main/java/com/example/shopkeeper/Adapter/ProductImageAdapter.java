package com.example.shopkeeper.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.shopkeeper.R;

/**
 * Created by dedel on 27/02/2017.
 */

public class ProductImageAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] mThumbIds = { R.drawable.logo,R.drawable.icon_128,R.drawable.logo, R.drawable.icon_128 };

    public ProductImageAdapter(Context mContext)
    {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup
            parent) {
        ImageView i = new ImageView(mContext);
        i.setImageResource(mThumbIds[position]);
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new Gallery.LayoutParams(
                Gallery.LayoutParams.WRAP_CONTENT,
                Gallery.LayoutParams.WRAP_CONTENT));
        i.setBackgroundResource(R.drawable.logo);
        return i;
    }


}
