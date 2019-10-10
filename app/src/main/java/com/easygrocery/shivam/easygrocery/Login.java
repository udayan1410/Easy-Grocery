package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easygrocery.shivam.Databases.DBhelper;
import com.easygrocery.shivam.Utils.Helper;

public class Login extends Activity implements View.OnClickListener {

    EditText name, password;
    TextView register;
    Button login;
    DBhelper db;
    String name2, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBhelper(this);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);


        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                name2 = name.getText().toString();
                password2 = password.getText().toString();

                if (db.readfromDB(name2, password2).size() == 1)
                {
                    Toast.makeText(this, "Welcome "+name2, Toast.LENGTH_SHORT).show();
                    Helper.savetoSharedPref(Login.this,"Login","username",name2);
                    startActivity(new Intent(Login.this,MainActivity.class));
                }
                else
                    Toast.makeText(this, "Please enter valid name and password", Toast.LENGTH_SHORT).show();
                break;

            case R.id.register:
                startActivity(new Intent(Login.this, Register.class));
                break;
        }
    }
}
