
package com.easygrocery.shivam.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryModel {

    @SerializedName("vegetables")
    @Expose
    private ArrayList<Vegetable> vegetables = null;
    @SerializedName("Fruits")
    @Expose
    private ArrayList<Fruit> fruits = null;
    @SerializedName("Foodgrains")
    @Expose
    private ArrayList<Foodgrain> foodgrains = null;
    @SerializedName("Meat")
    @Expose
    private ArrayList<Meat> meat = null;

    public ArrayList<Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(ArrayList<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }

    public ArrayList<Foodgrain> getFoodgrains() {
        return foodgrains;
    }

    public void setFoodgrains(ArrayList<Foodgrain> foodgrains) {
        this.foodgrains = foodgrains;
    }

    public ArrayList<Meat> getMeat() {
        return meat;
    }

    public void setMeat(ArrayList<Meat> meat) {
        this.meat = meat;
    }

}
