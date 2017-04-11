package com.example.shopkeeper.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.shopkeeper.Model.ProductDescription;
import com.example.shopkeeper.R;

public class EditCatalogChangeDescription extends AppCompatActivity {

    private EditText ETtitle;
    private EditText ETdescription;
    ProductDescription description;
    private boolean saved;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_catalog_change_description);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);

        Bundle bundle = getIntent().getExtras();
        this.description = bundle.getParcelable("description");
        this.position = bundle.getInt("position");

        ETtitle = (EditText) findViewById(R.id.title);
        ETdescription = (EditText) findViewById(R.id.description);
        if (!description.getMtitle().isEmpty()) {
            ETtitle.setText(description.getMtitle());
        }
        if (!description.getMdescription().isEmpty()) {
            ETdescription.setText(description.getMdescription());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the aaction bar if it is present
        getMenuInflater().inflate(R.menu.menu_save2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_save:
                saved = true;
                description.setMtitle(ETtitle.getText().toString());
                description.setMdescription(ETdescription.getText().toString());
                return true;
            case R.id.menu_exit:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
            intent.putExtra("description", description);
            intent.putExtra("pos",position);
        if(saved) {
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
