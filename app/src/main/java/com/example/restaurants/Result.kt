package com.example.restaurants

import com.example.restaurants.model.Restaurant
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result{

    @SerializedName("results")
    @Expose
    private var restaurantsList: MutableList<Restaurant> = mutableListOf()

    public fun setRestaurantsList(niz: MutableList<Restaurant>) {
        this.restaurantsList = niz
    }

    public fun getRestaurantsList():MutableList<Restaurant>{
        return restaurantsList
    }


}