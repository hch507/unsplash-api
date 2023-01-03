package com.example.unsplash_app_tutorial.recyclerview

import android.view.View
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplash_app_tutorial.App
import com.example.unsplash_app_tutorial.R
import com.example.unsplash_app_tutorial.model.Photo
import kotlinx.android.synthetic.main.layout_photo_item.view.*

class PhotoItemViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {

    private val photoImageView = itemView.photo_image
    private val photoCreateAtText = itemView.create_at_text
    private val photoLikesCountText =itemView.likes_count_text
    fun bindWithView(photoItem : Photo){
        photoCreateAtText.text = photoItem.createAt

        photoLikesCountText.text = photoItem.likeCount.toString()

        Glide.with(App.instance)
            .load(photoItem.thumbnail)
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(photoImageView)
    }
}