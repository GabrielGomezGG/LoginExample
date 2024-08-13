package com.gg.loginexample.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.gg.loginexample.domain.model.Post
import com.gg.loginexample.fake.FakeModels
import com.gg.loginexample.ui.theme.LoginExampleTheme

@Composable
fun HomeScreen(posts: List<Post>) {
    LazyColumn {
        items(posts) { post ->
            ListItem(
                headlineContent = {
                    Text(text = post.title)
                },
                supportingContent = {
                    Text(text = post.body)
                },
            )
        }
    }
}

@PreviewLightDark
//@PreviewFontScale
//@PreviewScreenSizes
@Composable
private fun HomeScreenPreview() {
    LoginExampleTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                posts = FakeModels.postFake
            )
        }
    }
}