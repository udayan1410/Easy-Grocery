package com.easygrocery.shivam.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Helper {


    public static void savetoSharedPref(Context c,String spname,String key,String value){
        SharedPreferences sp;
        SharedPreferences.Editor editor;

        sp=c.getSharedPreferences(spname,c.MODE_PRIVATE);
        editor=sp.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getfromSharedPref(Context c,String spname,String key){
        SharedPreferences sp;
        sp = c.getSharedPreferences(spname,c.MODE_PRIVATE);

        return sp.getString(key,"");
    }

    public static String getPrice(String price){

        String finalstr="";

        for(int i=3;i<price.length();i++){
            if(!(price.charAt(i)==' '))
                finalstr+=price.charAt(i);
            else
                break;
        }

        Log.d("STR arr",""+finalstr);
     return finalstr;
    }
}
