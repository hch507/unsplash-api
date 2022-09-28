package com.example.unsplash_app_tutorial.retrofit

import android.util.Log
import com.example.unsplash_app_tutorial.utils.API
import com.example.unsplash_app_tutorial.utils.Constant.TAG
import com.example.unsplash_app_tutorial.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
    }
    //레트로핏 인터페이스 가져오기
    private var iRetrofit : IRetrofit? =RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //api호출
    fun searchPhotos(searchTerm : String?, completion :(RESPONSE_STATE, String) -> Unit){

        val call = iRetrofit?.searchPhotos(searchTerm=searchTerm).let{
            it
        }?: return

        call.enqueue(object:retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                Log.d(TAG, "RetrofitManager-onResponse() called / response : ${response.body()}")
                completion(RESPONSE_STATE.OKAY,response.body().toString())
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

                Log.d(TAG, "RetrofitManager-onFailure() called/t:$t")
                completion(RESPONSE_STATE.FAIL,t.toString())
            }

        })
    }
}