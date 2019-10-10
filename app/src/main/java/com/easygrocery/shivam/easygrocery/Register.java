package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easygrocery.shivam.Databases.DBhelper;


public class Register extends Activity implements View.OnClickListener {

    EditText name,password,phone,email;
    Button submit;
    DBhelper db;
    ImageView back;
    TextView actionBarText;
    String name2,password2,phone2,email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    public void init(){


        db=new DBhelper(Register.this);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        submit=findViewById(R.id.submit);
        back = findViewById(R.id.back);
        actionBarText = findViewById(R.id.actionbarText);
        actionBarText.setText("Register");
        back.setOnClickListener(onClickListener);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                name2=name.getText().toString();
                password2=password.getText().toString();
                phone2=phone.getText().toString();
                email2=email.getText().toString();

                if(validate()){

                    db.addtoDB(name2,password2,phone2,email2);
                    Toast.makeText(this, "Your account is created successfully!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }


    public boolean validate() {
        boolean flag = true;

        if (name2.length() < 2  || db.readfromDB(name2).size()==1) {
            name.setError("Invalid Username");
            flag = false;
        }
        if (email2.indexOf("@") <= 0 || email2.indexOf(".") <= 0 || email2.length() < 9) {
            email.setError("Please enter valid email");
            flag = false;
        }
        if (phone2.length() != 10) {
            phone.setError("Please enter 10-digit number");
            flag = false;
        }
        if (password2.length() < 2) {
            password.setError("Password must be more than 1 character");
            flag = false;
        }
        return flag;
    }

    public  View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
