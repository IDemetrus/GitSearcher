package com.example.gitsearcher.api

import com.example.gitsearcher.model.Repo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubApi {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("repositories")
    suspend fun getRepos(
        @Query("since") query: Int
    ): MutableList<Repo>
}