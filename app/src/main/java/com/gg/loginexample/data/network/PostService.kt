package com.gg.loginexample.data.network

import com.gg.loginexample.data.network.model.PostApi
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPostsNetwork(): Response<List<PostApi>>
}