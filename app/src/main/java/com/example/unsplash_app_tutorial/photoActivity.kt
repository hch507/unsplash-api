package com.example.unsplash_app_tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.recyclerview.PhotoGridRecyclerViewAdapter
import com.example.unsplash_app_tutorial.utils.Constant.TAG
import kotlinx.android.synthetic.main.activity_photo.*

class photoActivity : AppCompatActivity() {

    var photoList = ArrayList<Photo>()
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val bundle=intent.getBundleExtra("bundle_array")
        val searchTerm = intent.getStringExtra("searchTerm")

        photoList = bundle?.getSerializable("array_list") as ArrayList<Photo>
        Log.d(TAG, "photoActivity - onCreate() - called / searchTerm : $searchTerm, photolist.count() : ${photoList.count()}")

        this.photoGridRecyclerViewAdapter=PhotoGridRecyclerViewAdapter()
        this.photoGridRecyclerViewAdapter.submitList(photoList)

        my_photo_recycle.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        my_photo_recycle.adapter=this.photoGridRecyclerViewAdapter
    }
}