package com.example.unsplash_app_tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.model.User
import com.example.unsplash_app_tutorial.utils.Constant

class userActivity : AppCompatActivity() {
    var userList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val bundle=intent.getBundleExtra("user_bundle_array")
        val searchTerm = intent.getStringExtra("searchTerm")

        userList = bundle?.getSerializable("user_array_list") as ArrayList<User>
        Log.d(Constant.TAG, "userActivity - onCreate() - called / searchTerm : $searchTerm, userlist.count() : ${userList.count()}")

    }
}