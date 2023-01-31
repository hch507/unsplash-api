package com.example.unsplash_app_tutorial.recyclerview
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplash_app_tutorial.App
import com.example.unsplash_app_tutorial.R
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.model.User
import kotlinx.android.synthetic.main.layout_photo_item.view.*
import kotlinx.android.synthetic.main.layout_user_item.view.*


class UseritemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val UserNameView = itemView.name
    private val UserInstagramView= itemView.instagram
    private val UserProfileView =itemView.user_image
    fun bindWithView(userItem : User) {
        UserNameView.text = userItem.username

        UserInstagramView.text = userItem.instagramname

        Glide.with(App.instance)
            .load(userItem.profile)
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(UserProfileView)
    }
}