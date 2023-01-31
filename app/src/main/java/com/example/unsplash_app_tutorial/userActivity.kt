package com.example.unsplash_app_tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.model.User
import com.example.unsplash_app_tutorial.recyclerview.PhotoGridRecyclerViewAdapter
import com.example.unsplash_app_tutorial.recyclerview.UserGridRecyclerViewAdapter
import com.example.unsplash_app_tutorial.utils.Constant
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.activity_user.*

class userActivity : AppCompatActivity() {
    var userList = ArrayList<User>()
    private lateinit var userGridRecyclerViewAdapter: UserGridRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val bundle=intent.getBundleExtra("user_bundle_array")
        val searchTerm = intent.getStringExtra("searchTerm")

        userList = bundle?.getSerializable("user_array_list") as ArrayList<User>
        Log.d(Constant.TAG, "userActivity - onCreate() - called / searchTerm : $searchTerm, userlist.count() : ${userList.count()}")


        this.userGridRecyclerViewAdapter= UserGridRecyclerViewAdapter()
        this.userGridRecyclerViewAdapter.submitList(userList)

        my_user_recycle.layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false)
        my_user_recycle.adapter=this.userGridRecyclerViewAdapter
    }
}