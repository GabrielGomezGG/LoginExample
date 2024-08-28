package com.gg.loginexample.data

import android.util.Log
import com.gg.loginexample.data.network.PostService
import com.gg.loginexample.data.network.model.toPost
import com.gg.loginexample.domain.model.Post
import com.gg.loginexample.exception.PostNotFoundException
import java.net.UnknownHostException
import javax.inject.Inject

interface PostRepository {
    suspend fun getPosts(): List<Post>
}

@Suppress("MagicNumber")
class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        try {
            val posts = postService.getPostsNetwork()

            if (posts.code() == 404)
                throw PostNotFoundException()

            return posts.body()?.map { it.toPost() } ?: emptyList()

        } catch (e: UnknownHostException) {
            Log.i("titi", e.message.toString())
            return emptyList()
        }

    }
}