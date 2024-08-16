package com.gg.loginexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gg.loginexample.domain.GetPostUseCase
import com.gg.loginexample.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val posts: List<Post>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val posts = _posts.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            try {
                _posts.value = HomeUiState.Success(getPostUseCase())
            } catch (e: Exception) {
                _posts.value = HomeUiState.Error(e.message ?: "An error occurred")
            }
        }
    }

}