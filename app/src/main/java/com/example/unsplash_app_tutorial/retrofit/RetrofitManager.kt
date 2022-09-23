package com.example.unsplash_app_tutorial.retrofit

import android.util.Log
import com.example.unsplash_app_tutorial.utils.API
import com.example.unsplash_app_tutorial.utils.Constant.TAG
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class RetrofitManager {

    //레트로핏 인터페이스 가져오기
    private var iRetrofit : IRetrofit? =RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //api호출
    fun searchPhotos(searchTerm : String?, completion :(String) -> Unit){

        val call = iRetrofit?.searchPhotos(searchTerm=searchTerm).let{
            it
        }?: return

        call.enqueue(object:retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                TODO("Not yet implemented")
                Log.d(TAG, "RetrofitManager-onResponse() called / response : ${response.raw()}")
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                TODO("Not yet implemented")
                Log.d(TAG, "RetrofitManager-onFailure() called/t:$t")
            }

        })
    }
}