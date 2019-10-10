package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easygrocery.shivam.Models.Foodgrain;
import com.easygrocery.shivam.Models.Fruit;
import com.easygrocery.shivam.Models.GroceryModel;
import com.easygrocery.shivam.Models.Meat;
import com.easygrocery.shivam.Models.ProductModel;
import com.easygrocery.shivam.Models.Vegetable;
import com.easygrocery.shivam.Network.API;
import com.easygrocery.shivam.Utils.DialogBox;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity2 extends Activity {

    ArrayList<String> arr;
    ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
    ArrayList<ProductModel> productModelArrayList2 = new ArrayList<>();
    ListView list;
    ImageView icon,clear;
    String category;
    Dialog mDialog;
    EditText search;
    CustomAdapter customAdapter = new CustomAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        search = findViewById(R.id.search);
        search.setEnabled(false);
        list = findViewById(R.id.list);
        category = getIntent().getStringExtra("category");
        icon = findViewById(R.id.icon);
        clear = findViewById(R.id.clear);
        clear.setVisibility(View.INVISIBLE);

        mDialog = DialogBox.getDialogbox(Activity2.this);
        mDialog.show();

        fetchData(category);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(search.getText().toString().equalsIgnoreCase("")) {
                    Intent i = new Intent(Activity2.this, product.class);
                    i.putExtra("name", productModelArrayList.get(position).getName());
                    i.putExtra("price", productModelArrayList.get(position).getPrice());
                    i.putExtra("image", productModelArrayList.get(position).getImage());
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(Activity2.this, product.class);
                    i.putExtra("name", productModelArrayList2.get(position).getName());
                    i.putExtra("price", productModelArrayList2.get(position).getPrice());
                    i.putExtra("image", productModelArrayList2.get(position).getImage());
                    startActivity(i);
                }
            }
        });

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setFocusableInTouchMode(true);
                search.requestFocus();
                search.setFocusable(true);
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(search,InputMethodManager.SHOW_IMPLICIT);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                productModelArrayList2.clear();
                if(!s.toString().equalsIgnoreCase("")) {
                    for (int i = 0; i < productModelArrayList.size(); i++) {
                        if (productModelArrayList.get(i).getName().indexOf(s.toString()) >= 0 || productModelArrayList.get(i).getName().toLowerCase().indexOf(s.toString())>=0) {
                            productModelArrayList2.add(productModelArrayList.get(i));
                            customAdapter.setArrayList(productModelArrayList2);
                            customAdapter.notifyDataSetChanged();
                            list.setAdapter(customAdapter);
                        }
                    }
                    clear.setVisibility(View.VISIBLE);
                }
                else{
                    customAdapter.setArrayList(productModelArrayList);
                    customAdapter.notifyDataSetChanged();
                    list.setAdapter(customAdapter);
                    clear.setVisibility(View.INVISIBLE);
                }

            }
        });

    }




    public void fetchData(final String category) {
        Log.d("category", category);
        Call<GroceryModel> call = API.getInstance().getData();
        call.enqueue(new Callback<GroceryModel>() {
            @Override
            public void onResponse(Call<GroceryModel> call, Response<GroceryModel> response) {
                GroceryModel groceryModel = response.body();
                initializeproductArray(category,groceryModel);
                customAdapter.setArrayList(productModelArrayList);
                list.setAdapter(customAdapter);
                mDialog.dismiss();
                search.setEnabled(true);
            }

            @Override
            public void onFailure(Call<GroceryModel> call, Throwable t) {

            }
        });
    }
    class CustomAdapter extends BaseAdapter {

        ArrayList<ProductModel> modelArrayList;

        public void setArrayList(ArrayList<ProductModel> modelArrayList){
            this.modelArrayList = modelArrayList;
        }

        @Override
        public int getCount() {
            return modelArrayList.size();
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
            convertView = getLayoutInflater().inflate(R.layout.generic_main, null);
            ImageView productImage = convertView.findViewById(R.id.img);
            TextView productName, productPrice;
            productName = convertView.findViewById(R.id.groc_name);
            productPrice = convertView.findViewById(R.id.groc_price);

            productName.setText(modelArrayList.get(position).getName());
            productPrice.setText(modelArrayList.get(position).getPrice());
            Glide.with(Activity2.this).load(modelArrayList.get(position).getImage()).into(productImage);

            return convertView;
        }
    }

public void initializeproductArray(String category,GroceryModel groceryModel){

    switch (category) {
        case "vegetables":
            ArrayList<Vegetable> vegetableArrayList = new ArrayList<>();
            vegetableArrayList = groceryModel.getVegetables();

            for (int i = 0; i < vegetableArrayList.size(); i++) {
                ProductModel productModel = new ProductModel();
                productModel.setImage(vegetableArrayList.get(i).getImage());
                productModel.setName(vegetableArrayList.get(i).getName());
                productModel.setPrice(vegetableArrayList.get(i).getPrice());
                productModelArrayList.add(productModel);
            }

            break;

        case "fruits":
            ArrayList<Fruit> fruitArrayList = new ArrayList<>();
            fruitArrayList = groceryModel.getFruits();

            for (int i = 0; i < fruitArrayList.size(); i++) {
                ProductModel productModel = new ProductModel();
                productModel.setImage(fruitArrayList.get(i).getImage());
                productModel.setName(fruitArrayList.get(i).getName());
                productModel.setPrice(fruitArrayList.get(i).getPrice());
                productModelArrayList.add(productModel);
            }
            break;

        case "foodgrains":
            ArrayList<Foodgrain> foodgrainsArrayList = new ArrayList<>();
            foodgrainsArrayList = groceryModel.getFoodgrains();

            for (int i = 0; i < foodgrainsArrayList.size(); i++) {
                ProductModel productModel = new ProductModel();
                productModel.setImage(foodgrainsArrayList.get(i).getImage());
                productModel.setName(foodgrainsArrayList.get(i).getName());
                productModel.setPrice(foodgrainsArrayList.get(i).getPrice());
                productModelArrayList.add(productModel);
            }
            break;

        case "meat":
            ArrayList<Meat> meatArrayList = new ArrayList<>();
            meatArrayList = groceryModel.getMeat();

            for (int i = 0; i < meatArrayList.size(); i++) {
                ProductModel productModel = new ProductModel();
                productModel.setImage(meatArrayList.get(i).getImage());
                productModel.setName(meatArrayList.get(i).getName());
                productModel.setPrice(meatArrayList.get(i).getPrice());
                productModelArrayList.add(productModel);
            }
            break;
    }
}
}
