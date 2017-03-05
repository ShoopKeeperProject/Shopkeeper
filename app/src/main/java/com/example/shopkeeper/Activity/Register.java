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

public class Register extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mPaswordEditText;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        mEmailEditText =(EditText) findViewById(R.id.EnterEmailAdd);
        mPaswordEditText = (EditText) findViewById(R.id.EnterPassword);
        mRegisterBtn = (Button)findViewById(R.id.register);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager userManager = UserManager.getInstance();
                userManager.signUp(mEmailEditText.getText().toString(), mPaswordEditText.getText().toString(), new CallBack<Void>() {
                    @Override
                    public void onResponse(Void result, ShooperKeeperException ex) {
                        if (null == ex){
                            onBackPressed();
                        } else {
                            Toast.makeText(Register.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


}
