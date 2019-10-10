package com.easygrocery.shivam.easygrocery;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easygrocery.shivam.Databases.DBhelper;
import com.easygrocery.shivam.Models.CartModel;
import com.easygrocery.shivam.Utils.Helper;

import java.util.ArrayList;

public class product extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ImageView img,back;
    TextView price_no,name,actionBarText;
    public Spinner spinner;
    Button button;
    CartModel m=new CartModel();
    DBhelper db;
    ArrayList<String> quantity = new ArrayList<>();
    String quant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        db = new DBhelper(product.this);
        img=findViewById(R.id.img);
        name=findViewById(R.id.name);
        price_no=findViewById(R.id.price_no);
        button=findViewById(R.id.addToB);
        back = findViewById(R.id.back);
        actionBarText = findViewById(R.id.actionbarText);
        actionBarText.setText("Easy Grocery");
        back.setOnClickListener(onClickListener);

        Intent i=getIntent();
        name.setText(i.getStringExtra("name"));
        price_no.setText(i.getStringExtra("price"));
        Glide.with(product.this).load(i.getStringExtra("image")).into(img);

        initSpinner();

        button.setOnClickListener(this);
    }

    public void initSpinner(){
        spinner = findViewById(R.id.spinner);


        quantity.add("1");
        quantity.add("2");
        quantity.add("3");

        ArrayAdapter<CharSequence> adapter = new  ArrayAdapter(this,
                 android.R.layout.simple_spinner_item,quantity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        quant =quantity.get(position);
        m.setQty(quantity.get(position));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.addToB:
               m.setUsername(Helper.getfromSharedPref(product.this,"Login","username"));
               m.setPrice( Helper.getPrice(getIntent().getStringExtra("price")));
               m.setItemname(getIntent().getStringExtra("name"));
               m.setImage(getIntent().getStringExtra("image"));
               m.setTotalprice(String.valueOf(Integer.parseInt(quant)*Integer.parseInt( Helper.getPrice(getIntent().getStringExtra("price")))));
               db.addtoDB(m);
               Toast.makeText(this, "Added sucessfully!!", Toast.LENGTH_SHORT).show();
               break;
       }
    }

    public  View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
