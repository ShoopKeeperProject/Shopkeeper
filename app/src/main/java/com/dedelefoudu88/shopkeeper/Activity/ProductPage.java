package com.dedelefoudu88.shopkeeper.Activity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dedelefoudu88.shopkeeper.Model.Product;
import com.dedelefoudu88.shopkeeper.Model.ProductDescription;
import com.dedelefoudu88.shopkeeper.R;

public class ProductPage extends AppCompatActivity {

    private ImageView mSwitcher;

    private Integer[] mImageIds = { R.drawable.prod1,R.drawable.prod2,R.drawable.prod1, R.drawable.prod2 };
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String CoName = preferences.getString("CoName", "");
        setTitle(CoName);

        ///
        Bundle bundle = getIntent().getExtras();
        this.product = bundle.getParcelable("product");


        mSwitcher = (ImageView) findViewById(R.id.ProductSwitcher);
        mSwitcher.setImageResource(mImageIds[0]);
        ///
        /*
        mSwitcher = (ImageSwitcher) findViewById(R.id.ProductSwitcher);
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));*/
        /*Gallery g = (Gallery) findViewById(R.id.ProductGallery);

        g.setAdapter(new ProductImageAdapter(this.getApplicationContext()));
        g.setOnItemSelectedListener(this);

        */


        LinearLayout galery = (LinearLayout) findViewById(R.id.ProductGallery);
        for (int i =0; i<4; i++)
        {
            ImageView Image = new ImageView(this);
            Image.setImageResource(mImageIds[i]);
            DisplayMetrics dm = Image.getResources().getDisplayMetrics();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(convertDpToPx(50, dm)/*LinearLayout.LayoutParams.WRAP_CONTENT*/, ViewGroup.LayoutParams.MATCH_PARENT);

            lp.setMargins(convertDpToPx(1, dm), convertDpToPx(1, dm), convertDpToPx(1, dm), convertDpToPx(1, dm));
            Image.setLayoutParams(lp);
            Image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Image.setTag(new Integer(i));
            Image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int nb = (int) v.getTag();
                    mSwitcher.setImageResource(mImageIds[nb]);
                }
            });
            galery.addView(Image);
        }
        /*
        ProductDescription[] descriptions;
        ProductManager.getInstance().updateProductDescription(product.getmId(),descriptions , new CallBack<Product>() {
            @Override
            public void onResponse(Product result, ShooperKeeperException ex) {
                if (null != ex){
                    Toast.makeText(ProductPage.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                product
            }


        });
        */

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

    private int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }

}
