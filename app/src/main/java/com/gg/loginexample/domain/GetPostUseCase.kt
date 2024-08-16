package com.gg.loginexample.domain

import com.gg.loginexample.data.PostRepository
import com.gg.loginexample.domain.model.Post
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): List<Post> {
        return postRepository.getPosts()
    }
}