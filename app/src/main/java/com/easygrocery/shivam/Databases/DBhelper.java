package com.easygrocery.shivam.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.easygrocery.shivam.Models.CartModel;
import com.easygrocery.shivam.Models.Model;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DBName = "UserDatabase";

    public DBhelper(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Data(name text primary key,password text,phone text,email text)");
        db.execSQL("Create table Cart(username text,ItemName text, qty text, price text, totalprice text,image text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists Data");
        db.execSQL("Drop table if exists Cart");
        onCreate(db);
    }


    public void addtoDB(String name, String password, String phone, String email) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("Insert or replace into Data values('" + name + "','" + password + "','" + phone + "','" + email + "')");
        db.close();
    }

    public void addtoDB(CartModel m){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("Insert or replace into Cart values('" +m.getUsername()+ "','" +m.getItemname()+ "','" +m.getQty()+ "','" +m.getPrice()+ "','" +m.getTotalprice()+ "','"+m.getImage()+"')");
        db.close();
    }

    public  ArrayList<CartModel> readCartData(String uname){
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<CartModel> arr = new ArrayList<>();

        Cursor c = db.rawQuery("Select * from Cart where username='"+uname+"'",null);
        while (c.moveToNext()) {
            CartModel m = new CartModel();

            m.setUsername(c.getString(c.getColumnIndex("username")));
            m.setItemname(c.getString(c.getColumnIndex("ItemName")));
            m.setQty(c.getString(c.getColumnIndex("qty")));
            m.setPrice(c.getString(c.getColumnIndex("price")));
            m.setTotalprice(c.getString(c.getColumnIndex("totalprice")));
            m.setImage(c.getString(c.getColumnIndex("image")));
            arr.add(m);
        }
        db.close();
        return  arr;
    }

    public ArrayList<Model> readfromDB(String userName, String userPassword) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Model> arr = new ArrayList<>();

        Cursor c = db.rawQuery("Select * from Data where name='"+userName+"' and password='"+userPassword+"' ", null);
        while (c.moveToNext()) {
            Model m = new Model();

            m.setName(c.getString(c.getColumnIndex("name")));
            m.setPassword(c.getString(c.getColumnIndex("password")));
            m.setPhone(c.getString(c.getColumnIndex("phone")));
            m.setEmail(c.getString(c.getColumnIndex("email")));
            arr.add(m);
        }
        db.close();
        return arr;
    }

    public ArrayList<Model> readfromDB(String userName) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Model> arr = new ArrayList<>();

        Cursor c = db.rawQuery("Select * from Data where name='"+userName+"'", null);
        while (c.moveToNext()) {
            Model m = new Model();

            m.setName(c.getString(c.getColumnIndex("name")));
            m.setPassword(c.getString(c.getColumnIndex("password")));
            m.setPhone(c.getString(c.getColumnIndex("phone")));
            m.setEmail(c.getString(c.getColumnIndex("email")));
            arr.add(m);
        }
        db.close();
        return arr;
    }

    public void deleteFromdb(String username,String itemname,String qty){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("Delete from Cart where username='"+username+"' and ItemName='"+itemname+"' and qty='"+qty+"'");
        db.close();
    }
}
