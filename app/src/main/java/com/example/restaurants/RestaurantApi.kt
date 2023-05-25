package com.example.restaurants

import com.example.restaurants.model.Restaurant
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

//https://www.triposo.com/api/20220411/poi.json?location_id=Paris&tag_labels=cuisine

interface RestaurantApi {
    @Headers(
        "Content-Type: application/json",
        "X-Triposo-Account: E9CHL2VB",
        "X-Triposo-Token: 81vio7opy5th0q8z5b3ch7whyophexp0"
    )

    @GET("20220411/poi.json?location_id=Paris&tag_labels=cuisine")
    suspend fun getRestaurants(): Response<Restaurant>

    @Headers(
        "Content-Type: application/json",
        "X-Triposo-Account: E9CHL2VB",
        "X-Triposo-Token: 81vio7opy5th0q8z5b3ch7whyophexp0"
    )
    @GET("20220411/poi.json")
    suspend fun getRestaurants(@Query("location_id") location_id : String,
                               @Query("tag_labels") tag_labels : String ): Response<Restaurant>
/*    fun getRestaurants(@Query("location_id") location_id : String,
                        @Query("tag_labels") tag_labels : String): Call<Restaurant>*/
}