package com.dedelefoudu88.shopkeeper.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dedelefoudu88.shopkeeper.Manager.CallBack;
import com.dedelefoudu88.shopkeeper.Manager.ShooperKeeperException;
import com.dedelefoudu88.shopkeeper.Manager.UserManager;
import com.dedelefoudu88.shopkeeper.R;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener{

    private EditText mEditText;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CoName", "");
        setTitle(name);

        mEditText = (EditText)findViewById(R.id.EmailAdd);
        mBtn = (Button)findViewById(R.id.SendForgetPassword);
        mBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SendForgetPassword:{
                UserManager.getInstance().forgetPassword(mEditText.getText().toString(), new CallBack<String>() {
                    @Override
                    public void onResponse(String result, ShooperKeeperException ex) {
                        String msg = null;
                        if (ex != null){
                            msg = ex.getMessage();
                            Toast.makeText(ForgetPassword.this, msg, Toast.LENGTH_LONG).show();
                        } else {
                            finish();
                        }
                    }
                });
                break;
            }
        }
    }

}
