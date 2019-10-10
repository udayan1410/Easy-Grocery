package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easygrocery.shivam.Utils.Helper;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {

    ImageView vegetables, fruits, foodgrains, meat,back;
    ArrayList<String> arrName;
    TextView actionBarText,username;
    ArrayList<Integer> arrIcon;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrName=new ArrayList<>();
        arrIcon=new ArrayList<>();

        setArrays();

        init();
    }

    public void setArrays(){
        arrName.add("Your Account");
        arrName.add("Your Basket");

        arrIcon.add(R.mipmap.account);
        arrIcon.add(R.mipmap.cart);
    }

    public void init(){
        list=findViewById(R.id.list);
        vegetables = findViewById(R.id.vegetables);
        fruits = findViewById(R.id.fruits);
        foodgrains = findViewById(R.id.foodgrains);
        meat = findViewById(R.id.meat);

        vegetables.setOnClickListener(this);
        fruits.setOnClickListener(this);
        foodgrains.setOnClickListener(this);
        meat.setOnClickListener(this);
        list.setAdapter(new CustomAdapter());
        list.setOnItemClickListener(this);
        back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
        actionBarText = findViewById(R.id.actionbarText);
        username = findViewById(R.id.nameText);
        actionBarText.setText("Easy Grocery");

        username.setText(Html.fromHtml("<u>Welcome "+Helper.getfromSharedPref(this,"Login","username")+"</u>"));


        Glide.with(this).load(R.drawable.vegetables).into(vegetables);
        Glide.with(this).load(R.drawable.fruits).into(fruits);
        Glide.with(this).load(R.drawable.foodgrains).into(foodgrains);
        Glide.with(this).load(R.drawable.meat).into(meat);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (arrName.get(position)){
            case "Your Account":
                startActivity(new Intent(MainActivity.this,Account.class));
                break;

            case "Your Basket":
                startActivity(new Intent(MainActivity.this,basket.class));
                break;
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        switch (v.getId()) {
            case R.id.vegetables:
                intent.putExtra("category", "vegetables");
                startActivity(intent);
                break;

            case R.id.fruits:
                    intent.putExtra("category", "fruits");
                startActivity(intent);
                break;

            case R.id.foodgrains:
                intent.putExtra("category", "foodgrains");
                startActivity(intent);
                break;

            case R.id.meat:
                intent.putExtra("category", "meat");
                startActivity(intent);
                break;
        }
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrName.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.generic_first, null);

            ImageView icon=convertView.findViewById(R.id.icon);
            TextView text=convertView.findViewById(R.id.text);

            text.setText(arrName.get(position));
            Glide.with(MainActivity.this).load(arrIcon.get(position)).into(icon);
            return convertView;
        }
    }
}
