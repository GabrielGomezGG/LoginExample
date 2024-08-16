package com.gg.loginexample.data.network

import com.gg.loginexample.fake.FakeModels
import com.google.gson.Gson
import core.Helper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var postService: PostService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        //instancia "falsa" de retrofit
        postService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }

    @After
    fun tearDown() {
        // "apaga" el mock server
        mockWebServer.shutdown()
    }

    @Test
    fun getPostsNetwork_WhenPostServiceIsCode200_ThenReturnPostList() = runTest {
        val mockResponse = MockResponse()

        //leer el json
//        val content = Helper.readFileResources("/FakePosts.json")
        val content = Gson().toJson(FakeModels.fakePostsApi)
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = postService.getPostsNetwork()
        mockWebServer.takeRequest()

        Assert.assertEquals(200, response.code())
        Assert.assertEquals(4, response.body()!!.size)
        Assert.assertEquals("Title 1", response.body()!!.first().title)
    }

    @Test
    fun getPostsNetwork_WhenPostServiceIsCode404_ThenReturnNull() = runTest {
        val mockResponse = MockResponse()

        mockResponse.setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        val response = postService.getPostsNetwork()
        mockWebServer.takeRequest()

        Assert.assertEquals(404, response.code())
        Assert.assertNull(response.body())
    }

}