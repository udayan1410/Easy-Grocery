package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easygrocery.shivam.Databases.DBhelper;
import com.easygrocery.shivam.Models.Model;
import com.easygrocery.shivam.Utils.Helper;

import java.util.ArrayList;

public class Account extends Activity {

    TextView txt1,txt2,actionBarText;
    TextView email,phone,name;
    ImageView back;
    DBhelper dBhelper;
    ArrayList<Model> modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        init();

    }

    public void init(){

        txt1=findViewById(R.id.txt1);
        txt1.setText(Html.fromHtml("<u>Email</u>"));
        txt2=findViewById(R.id.txt2);
        txt2.setText(Html.fromHtml("<u>Phone</u>"));
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        name = findViewById(R.id.name);

        dBhelper = new DBhelper(this);
        modelArrayList = dBhelper.readfromDB(Helper.getfromSharedPref(this,"Login","username"));

        email.setText(modelArrayList.get(0).getEmail());
        phone.setText(modelArrayList.get(0).getPhone());
        name.setText(modelArrayList.get(0).getName());


        back = findViewById(R.id.back);
        actionBarText = findViewById(R.id.actionbarText);
        actionBarText.setText("Easy Grocery");
        back.setOnClickListener(onClickListener);




    }

    public  View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
