package com.gg.loginexample.di

import com.gg.loginexample.data.PostRepository
import com.gg.loginexample.data.PostRepositoryImpl
import com.gg.loginexample.data.network.PostService
import com.gg.loginexample.domain.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePostService(
        retrofit: Retrofit
    ): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Singleton
    @Provides
    fun providePostRepository(
        postService: PostService
    ): PostRepository {
        return PostRepositoryImpl(postService)
    }

    @Singleton
    @Provides
    fun provideGetPostUseCase(
        postRepository: PostRepository
    ): GetPostUseCase {
        return GetPostUseCase(postRepository)
    }

}