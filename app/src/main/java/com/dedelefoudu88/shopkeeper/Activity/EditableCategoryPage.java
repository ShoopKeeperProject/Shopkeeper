package com.dedelefoudu88.shopkeeper.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dedelefoudu88.shopkeeper.Model.Category;
import com.dedelefoudu88.shopkeeper.R;

public class EditableCategoryPage extends AppCompatActivity {

    private ImageView image;

    private Category category;
    private boolean dataChange;
    private int position;
    EditText categoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_category_page);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);

        Bundle bundle = getIntent().getExtras();
        this.category = bundle.getParcelable("category");
        this.position = bundle.getInt("position");

        image = (ImageView) findViewById(R.id.CategoryImage);
        image.setImageResource(R.drawable.icon_128);

        dataChange = false;

        categoryName = (EditText) findViewById(R.id.CategoryName);
        if (!category.getmName().isEmpty()) {
            categoryName.setText(category.getmName());
        }
        if (category.getmImageURL() != null && !category.getmImageURL().isEmpty()){
            Glide.with(getApplicationContext())
                    .load(category.getmImageURL())
                    .into(image);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_save:
                dataChange = true;
                category.setmName(categoryName.getText().toString());
                return true;
            case R.id.menu_exit:
                onBackPressed();
                return true;
            case R.id.menu_delete:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.Warning)
                        .setMessage(R.string.WarningMessageDelete)
                        .setPositiveButton(R.string.Ok,
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialoginterface, int i)
                                    {
                                        //
                                        dataChange = true;
                                        category.setIsEnable(false);
                                        EditableCategoryPage.this.onBackPressed();

                                    }
                                })
                        .setNegativeButton(R.string.Cancel,
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialoginterface, int i)
                                    {

                                    }
                                })
                        .show();

                return true;
        }
        return false;
    }


    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra("category", category);
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

