package com.example.gitsearcher.di

import com.example.gitsearcher.api.GitHubApi
import com.example.gitsearcher.other.Constants.GITHUB_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(GITHUB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideGitHubApi(retrofit: Retrofit):GitHubApi =
        retrofit.create(GitHubApi::class.java)
}