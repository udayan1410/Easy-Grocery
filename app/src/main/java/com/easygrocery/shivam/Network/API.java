package com.easygrocery.shivam.Network;

import com.easygrocery.shivam.Models.GroceryModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class API {

    private static GroceryInterface groceryInterface=null;
    public static final String URL="https://api.myjson.com/bins/";


    public static GroceryInterface getInstance() {
        if (groceryInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            groceryInterface = retrofit.create(GroceryInterface.class);
        }
        return groceryInterface;
    }

    public interface GroceryInterface{
        @GET("19fuiz")
        Call<GroceryModel> getData();
    }
}
