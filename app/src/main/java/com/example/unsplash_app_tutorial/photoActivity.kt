package com.example.unsplash_app_tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.utils.Constant.TAG

class photoActivity : AppCompatActivity() {

    var photoList = ArrayList<Photo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val bundle=intent.getBundleExtra("bundle_array")
        val searchTerm = intent.getStringExtra("searchTerm")

        photoList = bundle?.getSerializable("array_list") as ArrayList<Photo>
        Log.d(TAG, "photoActivity - onCreate() - called / searchTerm : $searchTerm, photolist.count() : ${photoList.count()}")
    }
}