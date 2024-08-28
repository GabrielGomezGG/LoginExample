package com.gg.loginexample.domain

import com.gg.loginexample.data.PostRepository
import com.gg.loginexample.exception.PostNotFoundException
import com.gg.loginexample.fake.FakeModels
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPostUseCaseTest{

    private lateinit var getPostUseCase: GetPostUseCase

    private val postRepository = mockk<PostRepository>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getPostUseCase = GetPostUseCase(postRepository)
    }

    @Test
    fun invoke_WhenPostRepositoryReturnPostList_ThenReturnPostList() = runTest {
        // Given
        val expected = FakeModels.fakePosts
        coEvery { postRepository.getPosts() } returns expected

        // When
        val result = getPostUseCase()

        // Then
        assertEquals("Title 1", result.first().title)
    }

    @Test(expected = PostNotFoundException::class)
    fun invoke_WhenPostRepositoryReturnException_ThenReturnSameException() = runTest {
        // Given
        coEvery { postRepository.getPosts() } throws PostNotFoundException()

        // When
        getPostUseCase()
    }
}