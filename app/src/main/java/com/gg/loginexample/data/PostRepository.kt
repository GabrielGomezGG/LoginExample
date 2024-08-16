package com.gg.loginexample.data

import com.gg.loginexample.data.network.PostService
import com.gg.loginexample.data.network.model.toPost
import com.gg.loginexample.domain.model.Post
import com.gg.loginexample.exception.PostNotFoundException
import java.net.UnknownHostException
import javax.inject.Inject

interface PostRepository {
    suspend fun getPosts(): List<Post>
}

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        try {
            val posts = postService.getPostsNetwork()

            if(posts.code() == 404)
                throw PostNotFoundException()

            if(posts.isSuccessful)
                return posts.body()?.map { it.toPost() } ?: emptyList()

            return emptyList()
        }catch (e: UnknownHostException){
            throw e
        }

    }
}