package com.example.gitsearcher.api

import com.example.gitsearcher.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubApi {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response
}