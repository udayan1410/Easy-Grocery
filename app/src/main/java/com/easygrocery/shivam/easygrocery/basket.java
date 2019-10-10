package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easygrocery.shivam.Databases.DBhelper;
import com.easygrocery.shivam.Models.CartModel;
import com.easygrocery.shivam.Utils.Helper;

import java.util.ArrayList;

public class basket extends Activity implements View.OnClickListener {

    ArrayList<CartModel> cartModelArrayList;
    ListView list;
    ImageView back;
    TextView actionBarText;
    Button button;
    TextView totalAmount;
    DBhelper dBhelper = new DBhelper(this);
    CustomAdapter customAdapter = new CustomAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


        init();
        getDataFromDB();

    }

    public void init() {

        cartModelArrayList = new ArrayList<>();
        list = findViewById(R.id.list);
        totalAmount = findViewById(R.id.totalamt);
        button = findViewById(R.id.proceed_to_buy);
        dBhelper = new DBhelper(this);
        button.setOnClickListener(this);
        back = findViewById(R.id.back);
        actionBarText = findViewById(R.id.actionbarText);
        actionBarText.setText("Your Basket");
        back.setOnClickListener(onClickListener);
    }

    public void getDataFromDB() {

        cartModelArrayList = dBhelper.readCartData(Helper.getfromSharedPref(this, "Login", "username"));
        list.setAdapter(customAdapter);

        int total = 0;
        for (int i = 0; i < cartModelArrayList.size(); i++) {
            total += Integer.parseInt(cartModelArrayList.get(i).getTotalprice());
        }
        totalAmount.setText("Rs." + total);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.proceed_to_buy:
                startActivity(new Intent(basket.this, Final.class));
                break;
        }
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return cartModelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.generic, null);

            CardView deleteButton = convertView.findViewById(R.id.deleteButton);

            ImageView img = convertView.findViewById(R.id.img);
            TextView groc_name = convertView.findViewById(R.id.groc_name);
            TextView groc_price = convertView.findViewById(R.id.groc_price);
            TextView groc_qty = convertView.findViewById(R.id.groc_qty);
            TextView groc_totalprice = convertView.findViewById(R.id.totalprice);

            Glide.with(basket.this).load(cartModelArrayList.get(position).getImage()).into(img);
            groc_name.setText(cartModelArrayList.get(position).getItemname());
            groc_price.setText(cartModelArrayList.get(position).getPrice());
            groc_qty.setText(cartModelArrayList.get(position).getQty());
            groc_totalprice.setText(cartModelArrayList.get(position).getTotalprice());


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dBhelper.deleteFromdb(cartModelArrayList.get(position).getUsername(), cartModelArrayList.get(position).getItemname(),cartModelArrayList.get(position).getQty());
                    getDataFromDB();
                }
            });

            return convertView;
        }
    }

    public  View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}

