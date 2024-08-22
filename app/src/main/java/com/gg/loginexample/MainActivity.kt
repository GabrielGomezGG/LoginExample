package com.gg.loginexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gg.loginexample.ui.HomeScreen
import com.gg.loginexample.ui.HomeUiState
import com.gg.loginexample.ui.HomeViewModel
import com.gg.loginexample.ui.theme.LoginExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            LoginExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val posts by homeViewModel.posts.collectAsState()

                    when (posts) {
                        is HomeUiState.Loading -> {
                            // Show loading
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is HomeUiState.Error -> {
                            // Show error
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = (posts as HomeUiState.Error).message)
                            }
                        }

                        is HomeUiState.Success -> {

                            val postItems = (posts as HomeUiState.Success).posts

                            HomeScreen(postItems)
                        }
                    }

                }
            }
        }
    }
}