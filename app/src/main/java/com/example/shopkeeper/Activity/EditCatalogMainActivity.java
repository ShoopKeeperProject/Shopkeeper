package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shopkeeper.Adapter.EditCatalogResiclerViewAdaptateur;
import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.ProductManager;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.Category;
import com.example.shopkeeper.Model.Product;
import com.example.shopkeeper.R;

import java.util.ArrayList;
import java.util.List;

public class EditCatalogMainActivity extends AppCompatActivity {

    String parentId;

    private EditCatalogResiclerViewAdaptateur mAdapter;
    // get item from this id
    ArrayList<Category> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_catalog_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);

        if (savedInstanceState == null) {

            // get parent id
            Bundle bundle = getIntent().getExtras();
            this.parentId = bundle.getString("parentId");

        } else { // savedInstanceState has saved values

            this.parentId = savedInstanceState.getString("parentId3");
        }

        // editing resicler view, item

        RecyclerView mResiclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mResiclerView.setItemAnimator(new DefaultItemAnimator());
        mResiclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));



        mAdapter = new EditCatalogResiclerViewAdaptateur(this,items);
        mResiclerView.setAdapter(mAdapter);

        ProductManager.getInstance().getList(parentId, null, new CallBack<List<Category>>() {
            @Override
            public void onResponse(List<Category> result, ShooperKeeperException ex) {
                if (null != ex){
                    Toast.makeText(EditCatalogMainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                mAdapter.addAll(result);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the aaction bar if it is present
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("parentId3", parentId);

        // etc.
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addCategory:
                Category item2 = new Category("",parentId,"","");
                Intent intent2 = new Intent(this,EditableCategoryPage.class);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("category", item2);
                bundle2.putInt("position", -1);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2,7);
                return true;
            case R.id.addItem:
                Product item3 = new Product("",parentId,"",0,0,"");
                Intent intent = new Intent(this,EditableProductPage.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("product", item3);
                bundle.putInt("position", -1);
                intent.putExtras(bundle);
                startActivityForResult(intent,5);
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                final Product[] product = {(Product) intent.getParcelableExtra("product")};
                items.set(intent.getIntExtra("position",-1), product[0]);
                //createOrUpdateProduct(Product product, final CallBack<Product> callBack)
                ProductManager.getInstance().createOrUpdateProduct(product[0], new CallBack<Product>() {
                    @Override
                    public void onResponse(Product result, ShooperKeeperException ex) {
                        if (null != ex){
                            Toast.makeText(EditCatalogMainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                            product[0] = result;
                            return;
                        }
                    }
                });
            }
        }
        else if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                final Product[] product = {(Product) intent.getParcelableExtra("product")};
                items.add( product[0]);
                mAdapter.notifyItemInserted(items.size()-1);
                //createOrUpdateProduct(Product product, final CallBack<Product> callBack)
                ProductManager.getInstance().createOrUpdateProduct(product[0], new CallBack<Product>() {
                    @Override
                    public void onResponse(Product result, ShooperKeeperException ex) {
                        if (null != ex){
                            Toast.makeText(EditCatalogMainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                            product[0] = result;
                            return;
                        }
                    }
                });
            }
        }
        else if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                final Category[] category = {(Category) intent.getParcelableExtra("category")};
                items.set(intent.getIntExtra("position",-1), category[0]);
                //createOrUpdateProduct(Product product, final CallBack<Product> callBack)
                ProductManager.getInstance().createOrUpdateCategory(category[0], new CallBack<Category>() {
                    @Override
                    public void onResponse(Category result, ShooperKeeperException ex) {
                        if (null != ex){
                            Toast.makeText(EditCatalogMainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                            category[0] = result;
                            return;
                        }
                    }
                });
            }
        }
        else if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                final Category[] category = {(Category) intent.getParcelableExtra("category")};
                items.add( category[0]);
                mAdapter.notifyItemInserted(items.size()-1);
                //createOrUpdateProduct(Product product, final CallBack<Product> callBack)
                ProductManager.getInstance().createOrUpdateCategory(category[0], new CallBack<Category>() {
                    @Override
                    public void onResponse(Category result, ShooperKeeperException ex) {
                        if (null != ex){
                            Toast.makeText(EditCatalogMainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                            category[0] = result;
                            return;
                        }
                    }
                });
            }
        }
    }


}
