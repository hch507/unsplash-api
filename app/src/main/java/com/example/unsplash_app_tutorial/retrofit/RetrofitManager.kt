package com.example.unsplash_app_tutorial.retrofit

import android.util.Log
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.model.User
import com.example.unsplash_app_tutorial.utils.API
import com.example.unsplash_app_tutorial.utils.Constant.TAG
import com.example.unsplash_app_tutorial.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
    }
    //레트로핏 인터페이스 가져오기
    private var iRetrofit : IRetrofit? =RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //api호출
    fun searchPhotos(searchTerm : String?, completion :(RESPONSE_STATE, ArrayList<Photo>?) -> Unit){

        val call = iRetrofit?.searchPhotos(key = API.CLIENT_ID, searchTerm=searchTerm).let{
            it
        }?: return
        //실제 요청 후 callback을 받₩
        call.enqueue(object:retrofit2.Callback<JsonElement>{
            //응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                Log.d(TAG, "RetrofitManager-onResponse() called / response : ${response.body()}")
                var parseDataArray = ArrayList<Photo>()
                response.body()?.let{
                    val body = it.asJsonObject
                    val results = body.getAsJsonArray("results")

                    results.forEach{
                        item->
                        val itemObject = item.asJsonObject
                        val user = itemObject.get("user").asJsonObject
                        //username 가져오기
                        val username : String= user.get("username").asString
                        //likeCount가져오기
                        val likesCount = itemObject.get("likes").asInt
                        //thumbnail가져오기
                        val thumbnaull = itemObject.get("urls").asJsonObject.get("thumb").asString
                        //creatAt가져오기
                        val createAt = itemObject.get("created_at").asString
                        // crrateAt parse
                        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
                        var outputDate =formatter.format(parser.parse(createAt))
                        Log.d(TAG, "RetrofitManager-onResponse() called,${outputDate}")
                        val photoItem = Photo(author = username, likeCount = likesCount, thumbnail = thumbnaull, createAt = createAt)
                        parseDataArray.add(photoItem)
                    }

                }
                completion(RESPONSE_STATE.OKAY,parseDataArray)
            }
            //응답실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

                Log.d(TAG, "RetrofitManager-onFailure() called/t:$t")
                completion(RESPONSE_STATE.FAIL,null)
            }

        })
    }

    fun searchUser(searchTerm : String?, completion :(RESPONSE_STATE, ArrayList<User>) -> Unit){

        val call = iRetrofit?.searchUser(key = API.CLIENT_ID,searchTerm=searchTerm).let{
            it
        }?: return
        var parseDataArray = ArrayList<User>()
        call.enqueue(object:retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                when (response.code()) {
                    200 -> {
                        Log.d(
                            TAG,
                            "RetrofitManager-onResponse() called / response : ${response.body()}"
                        )

                        response.body()?.let {
                            val body = it.asJsonObject
                            val result = body.getAsJsonArray("results")

                            result.forEach { item ->
                                val itemObject = item.asJsonObject
                                // name 가져오기
                                val name = itemObject.get("username").asString
                                //instagram name 가져오기
                                var instagramname : String?= ""
                                if(itemObject.get("instagram_username").isJsonNull()){
                                    instagramname="정보없음"
                                }
                                else {
                                    instagramname = itemObject.get("instagram_username").asString
                                }
                                //profile가져오기
                                val profile =
                                    itemObject.get("profile_image").asJsonObject.get("medium").asString

                                val userItem = User(
                                    username = name,
                                    instagramname = instagramname,
                                    profile = profile
                                )

                                parseDataArray.add(userItem)

                            }
                            completion(RESPONSE_STATE.OKAY, parseDataArray)

                        }
                    }

                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

                Log.d(TAG, "RetrofitManager-onFailure() called/t:$t")
                completion(RESPONSE_STATE.FAIL,parseDataArray)
            }

        })
        }
    }

