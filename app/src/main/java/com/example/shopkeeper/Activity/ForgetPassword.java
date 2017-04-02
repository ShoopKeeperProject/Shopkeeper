package com.example.shopkeeper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Manager.UserManager;
import com.example.shopkeeper.R;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener{

    private EditText mEditText;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
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
