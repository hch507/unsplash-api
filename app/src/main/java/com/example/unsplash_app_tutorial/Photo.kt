package com.example.unsplash_app_tutorial

import java.io.Serializable

data class Photo(var thumbnail : String?,
                 var author : String?,
                 var createAt : String?,
                 var likeCount : Int?):Serializable{


}

