package com.example.unsplash_app_tutorial.retrofit


import com.example.unsplash_app_tutorial.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    //https://unsplash.com/searchphotos/?query="searchTerm"
    @GET(API.SEARCH_PHOTOS)
    fun searchPhotos(@Query("query") searchTerm :String?) : Call<JsonElement>



    //https://unsplash.com/searchusers/?query="searchTerm "
    @GET(API.SEARCH_USERS)
    fun searchUser(@Query("query") searchTerm :String?) :Call<JsonElement>


}