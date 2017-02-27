package com.example.shopkeeper.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.shopkeeper.Adapter.ProductImageAdapter;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.Model.ProductDescription;
import com.example.shopkeeper.R;

public class ProductPage extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {

    private ImageSwitcher mSwitcher;

    private Integer[] mImageIds = { R.drawable.logo,R.drawable.icon_128,R.drawable.logo, R.drawable.icon_128 };
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        ///
        Bundle bundle = getIntent().getExtras();
        this.product = bundle.getParcelable("product");

        ///

        mSwitcher = (ImageSwitcher) findViewById(R.id.ProductSwitcher);
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
        Gallery g = (Gallery) findViewById(R.id.ProductGallery);

        g.setAdapter(new ProductImageAdapter(this.getApplicationContext()));
        g.setOnItemSelectedListener(this);


        LinearLayout ProductDescription = (LinearLayout) findViewById(R.id.ProductDescription);
        for(int i=0; i<this.product.GetProductDescriptionSize();i++)
        {
            ProductDescription productDescription= this.product.getmDescription(i);
            TextView title = new TextView(this);
            TextView description = new TextView(this);;
            title.setText(productDescription.getMtitle());
            title.setTextColor(0xff000000);
            title.setTypeface(null, Typeface.BOLD);
            description.setText(productDescription.getMdescription());
            ProductDescription.addView(title);
            ProductDescription.addView(description);

        }

        TextView price = (TextView) findViewById(R.id.productPrice);
        price.setText(String.format("%.2f",product.getmPrice()));
        TextView taxe = (TextView) findViewById(R.id.productTaxe);
        taxe.setText(String.format("%.1f",product.getmTaxe()));



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {
        mSwitcher.setImageResource(mImageIds[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public View makeView() {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT,ImageSwitcher.LayoutParams.MATCH_PARENT));
        return i;
    }
}
