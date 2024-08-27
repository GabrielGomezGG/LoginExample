package com.gg.loginexample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gg.loginexample.domain.model.Post

@Composable
fun HomeScreen(posts: List<Post>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize().padding(4.dp)
    ) {
        items(
            posts,
            key = { post -> post.idPost }
        ) { post ->
            ListItem(
                headlineContent = {
                    Text(text = post.title)
                },
                supportingContent = {
                    Text(text = post.body)
                },
                shadowElevation = 4.dp,
                tonalElevation = 2.dp,
            )
        }
    }
}

//@PreviewLightDark
////@PreviewFontScale
////@PreviewScreenSizes
//@Composable
//private fun HomeScreenPreview() {
//    LoginExampleTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            HomeScreen(
//                posts = FakeModels.fakePosts
//            )
//        }
//    }
//}