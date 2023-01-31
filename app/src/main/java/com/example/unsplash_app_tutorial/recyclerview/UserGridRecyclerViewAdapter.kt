package com.example.unsplash_app_tutorial.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash_app_tutorial.R
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.model.User

class UserGridRecyclerViewAdapter:RecyclerView.Adapter<UseritemViewHolder>() {
    private var useritem = ArrayList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UseritemViewHolder {
        val UseritemViewHolder = UseritemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_user_item,parent,false))
        return UseritemViewHolder
    }

    override fun onBindViewHolder(holder: UseritemViewHolder, position: Int) {
        holder.bindWithView(this.useritem[position])
    }

    override fun getItemCount(): Int {
        return this.useritem.size
    }
    fun submitList(userList:ArrayList<User>){
        this.useritem=userList
    }

}