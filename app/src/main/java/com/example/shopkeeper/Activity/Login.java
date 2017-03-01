package com.example.shopkeeper.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.shopkeeper.Methode.CheckPassword;
import com.example.shopkeeper.R;

public class Login extends AppCompatActivity {

    boolean remeberMe = false;
    boolean defaultNameCheck = false;

    String defaultName;

    EditText userNamev;
    EditText passwordv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNamev = (EditText) findViewById(R.id.userName);
        passwordv = (EditText) findViewById(R.id.password);

        final Button LoginButton = (Button) findViewById(R.id.Login);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onLoginClick();
            }
        });

        final CheckBox remeberCheckBox = (CheckBox) findViewById(R.id.RemeberMe);
        remeberCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remeberMe = remeberCheckBox.isChecked();
            }
        });

        final Button RegisterButton = (Button) findViewById(R.id.EnterRegister);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onRegisterClick();
            }
        });

        final Button ForgetPasswordButton = (Button) findViewById(R.id.forgetPassword);
        ForgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ForgetPassword.class);
                //Bundle bundle = new Bundle();
                //bundle.putString("seller/customer", "seller");
                startActivity(intent);
            }
        });
    }

    protected void onRegisterClick()
    {
        Intent intent = new Intent(getApplicationContext(),Register.class);
        //Bundle bundle = new Bundle();
        //bundle.putString("seller/customer", "seller");
        startActivity(intent);
    }

    protected void onLoginClick()
    {
        if (defaultNameCheck)
        {
            if (defaultName.equals(userNamev.getText().toString())) {
                Intent intent = new Intent(getApplicationContext(), MainSeller.class);
                //Bundle bundle = new Bundle();
                //bundle.putString("seller/customer", "seller");
                startActivity(intent);
                return;
            }
        }

        CheckPassword checkPassword = new CheckPassword();
        String password = passwordv.getText().toString();;
        String name = userNamev.getText().toString();

        if (checkPassword.checkPassword(name,password))
        {
            if (remeberMe)
            {
                defaultName = name;
                defaultNameCheck = true;
                savePreferences(defaultName,defaultNameCheck);
            }
            Intent intent = new Intent(getApplicationContext(),MainSeller.class);
            //Bundle bundle = new Bundle();
            //bundle.putString("seller/customer", "seller");
            startActivity(intent);
        }
        else
        {
            new AlertDialog.Builder(Login.this)
                    .setTitle(R.string.IncorrectLogin)
                    .setMessage(R.string.WrongPasswordOrName)
                    .setPositiveButton(R.string.Ok,
                            new DialogInterface.OnClickListener(){
                                public void onClick(
                                        DialogInterface dialoginterface, int i){
                                }
                            })
                    .show();
            //Toast.makeText(Login.this, R.string.WrongPasswordOrName,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        loadPreferences();
    }

    public void savePreferences(String h,boolean checked) {
        SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
        pref.edit().putString("name", h).apply();
        pref.edit().putBoolean("checked", checked).apply();
    }

    public void loadPreferences() {
        SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
        defaultNameCheck = pref.getBoolean("checked", false);
        defaultName = pref.getString("name", "0");
        if (defaultNameCheck) {
            userNamev.setText(defaultName);
            passwordv.setText("xxxx");
        }
    }

    public void onCheckboxClicked(View view) {
// Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
// Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.RemeberMe:
                remeberMe = checked;
// TODO: Veggie sandwich
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
