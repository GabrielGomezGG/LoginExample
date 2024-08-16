package com.gg.loginexample.ui

import app.cash.turbine.test
import com.gg.loginexample.domain.GetPostUseCase
import com.gg.loginexample.exception.PostNotFoundException
import com.gg.loginexample.fake.FakeModels
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest{

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel

    private val getPostUseCase = mockk<GetPostUseCase>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        homeViewModel = HomeViewModel(getPostUseCase)
    }

    @Test
    fun `cuando getPost se ejecute correctamente, _posts debe de pasar de loading a success con la lista de posts`() = runTest {

        // Given
        val expected = HomeUiState.Success(FakeModels.fakePosts)
        val actual = homeViewModel.posts

        coEvery { getPostUseCase() } returns FakeModels.fakePosts

        // When
        homeViewModel.getPosts()

        // Then
        actual.test {
            val actualPost = awaitItem()
            assertEquals(expected, actualPost)
        }
    }

    @Test
    fun `cuando getPost se ejecute y falle, _posts debe de pasar de loading a error con el mensaje de error`() = runTest {

        // Given
        val expected = HomeUiState.Error("Post not found")
        val actual = homeViewModel.posts

        coEvery { getPostUseCase() } throws PostNotFoundException()

        // When
        homeViewModel.getPosts()

        // Then
        actual.test {
            val actualPost = awaitItem()
            assertEquals(expected, actualPost)
        }
    }
}