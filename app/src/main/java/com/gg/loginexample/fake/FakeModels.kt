package com.gg.loginexample.fake

import com.gg.loginexample.domain.model.Post

object FakeModels {

    val postFake = listOf(
        Post(
            idPost = 1,
            userId = 1,
            title = "Title 1",
            body = "Body 1"
        ),
        Post(
            idPost = 2,
            userId = 2,
            title = "Title 2",
            body = "Body 2"
        ),
        Post(
            idPost = 3,
            userId = 3,
            title = "Title 3",
            body = "Body 3"
        ),
        Post(
            idPost = 4,
            userId = 4,
            title = "Title 4",
            body = "Body 4"
        ),

    )

}