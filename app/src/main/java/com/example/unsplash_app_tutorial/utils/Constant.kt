package com.example.unsplash_app_tutorial.utils

object Constant {
    const val TAG: String = "로그"
}

enum class SEARCH_TYPE {
    PHOTO,
    USER
}
enum class RESPONSE_STATE{
    OKAY,
    FAIL
}

object API{
    const val BASE_URL : String = "https://api.unsplash.com/"

    const val CLIENT_ID ="x7eS2UmTg22vNWTxBH3FVGWL2oKo65ETcrsqT9KZa94"

    const val SEARCH_PHOTOS ="search/photos"
    const val SEARCH_USERS ="search/users"
}