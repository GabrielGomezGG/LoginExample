package com.gg.loginexample.domain.model

import com.gg.loginexample.data.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): List<Post> {
        return postRepository.getPosts()
    }
}