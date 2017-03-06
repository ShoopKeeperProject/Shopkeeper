package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.Model.ProductDescription;
import com.example.shopkeeper.R;

public class EditableProductPage extends AppCompatActivity {

    private ImageView mSwitcher;

    private Integer[] mImageIds = { R.drawable.logo,R.drawable.icon_128,R.drawable.logo, R.drawable.icon_128 };
    private Product product;
    private boolean dataChange;
    private int position;
    EditText ItemName;
    EditText price;
    EditText taxe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_product_page);

        Bundle bundle = getIntent().getExtras();
        this.product = bundle.getParcelable("product");
        this.position = bundle.getInt("position");

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
        dataChange = false;

        ItemName = (EditText) findViewById(R.id.ItemName);
        if (!product.getmName().isEmpty()) {
            ItemName.setText(product.getmName());
        }
        LinearLayout galery = (LinearLayout) findViewById(R.id.ProductGallery);
        for (int i =0; i<4; i++)
        {
            ImageView Image = new ImageView(this);
            Image.setImageResource(mImageIds[i]);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            DisplayMetrics dm = Image.getResources().getDisplayMetrics();
            lp.setMargins(convertDpToPx(5, dm), convertDpToPx(1, dm), convertDpToPx(5, dm), convertDpToPx(1, dm));
            Image.setLayoutParams(lp);
            Image.setScaleType(ImageView.ScaleType.FIT_XY);
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

        price = (EditText) findViewById(R.id.productPrice);
        price.setText(String.format("%.2f",product.getmPrice()));
        taxe = (EditText) findViewById(R.id.productTaxe);
        taxe.setText(String.format("%.1f",product.getmTaxe()));

        Button addDescription = (Button) findViewById(R.id.addDescription);
        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditCatalogChangeDescription.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("description",new ProductDescription("",""));
                bundle.putInt("position",-1);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
                //startActivity(intent);
            }
        });


    }

    @Override
    public void onResume()
    {
        super.onResume();

        LinearLayout ProductDescription = (LinearLayout) findViewById(R.id.ProductDescription);
        ProductDescription.removeAllViews();
        for(int i=0; i<this.product.GetProductDescriptionSize();i++)
        {
            com.example.shopkeeper.Model.ProductDescription productDescription= this.product.getmDescription(i);
            TextView title = new TextView(this);
            TextView description = new TextView(this);
            description.setTag(new Integer(i));
            title.setText(productDescription.getMtitle());
            title.setTextColor(0xff000000);
            title.setTypeface(null, Typeface.BOLD);
            description.setText(productDescription.getMdescription());
            ProductDescription.addView(title);
            ProductDescription.addView(description);
            description.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = (int) v.getTag();
                    Intent intent = new Intent(getApplicationContext(),EditCatalogChangeDescription.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("description",product.getmDescription(pos));
                    bundle.putInt("position",pos);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 3);
                    //startActivity(intent);
                    return false;
                }
            });
        }
    }

    private int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_save:
                dataChange = true;
                product.setmName(ItemName.getText().toString());
                product.setmPrice(Double.parseDouble(price.getText().toString()));
                product.setmTaxe(Double.parseDouble(taxe.getText().toString()));
                return true;
            case R.id.menu_exit:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                product.AddProductDescription((ProductDescription) intent.getParcelableExtra("description"));

            }
        }
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                product.setmDescription((ProductDescription) intent.getParcelableExtra("description"), intent.getIntExtra("pos",-1));

            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra("product", product);
        intent.putExtra("position", position);
        if(dataChange) {
            setResult(RESULT_OK, intent);
        }
        else
        {
            setResult(RESULT_CANCELED, intent);
        }
        super.onBackPressed();
        finish();
    }
}