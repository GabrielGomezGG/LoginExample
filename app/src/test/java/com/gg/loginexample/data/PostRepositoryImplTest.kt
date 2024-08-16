package com.gg.loginexample.data

import com.gg.loginexample.data.network.PostService
import com.gg.loginexample.data.network.model.PostApi
import com.gg.loginexample.exception.PostNotFoundException
import com.gg.loginexample.fake.FakeModels
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PostRepositoryImplTest {

    // Clase que se va a testear
    private lateinit var postRepositoryImpl: PostRepository

    //Dos maneras de mockear una clase/interface
    @MockK
    private lateinit var postService: PostService
//    private var postService = mockk<PostService>()

    @Before
    fun setUp() {
        // Inicializa las anotaciones de MockK
        MockKAnnotations.init(this)

        // Inicializa la clase a testear
        postRepositoryImpl = PostRepositoryImpl(postService)
    }

    @Test
    fun getPosts_WhenPostServiceIsisSuccessful_ThenReturnPostList() = runTest {
        // Given
        val expected = FakeModels.fakePostsApi
        val mockExpected = Response.success(expected)
        coEvery { postService.getPostsNetwork() } returns mockExpected

        // When
        val result = postRepositoryImpl.getPosts()

        // Then
        assertEquals("Title 1", result.first().title)
    }

    @Test(expected = PostNotFoundException::class)
    fun getPosts_WhenPostServiceIsNotSuccessful_ThenReturnPostNotFoundException() = runTest {
        // Given
        val responseBody = "".toResponseBody(null)
        val mockExpected = Response.error<List<PostApi>>(404, responseBody)
        coEvery { postService.getPostsNetwork() } returns mockExpected

        // When
        postRepositoryImpl.getPosts()
    }

}