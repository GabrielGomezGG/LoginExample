package com.gg.loginexample.data.network.model

import com.gg.loginexample.domain.model.Post
import com.google.gson.annotations.SerializedName

data class PostApi(
    val userId: Int,
    @SerializedName("id")
    val idPost: Int,
    val title: String,
    val body: String
)

fun PostApi.toPost(): Post {
    return Post(
        userId = this.userId,
        idPost = this.idPost,
        title = this.title,
        body = this.body
    )
}