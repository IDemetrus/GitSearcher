package com.example.gitsearcher.api

import com.example.gitsearcher.model.Repo
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApi {
    @GET("repositories")
    suspend fun getRepos(): MutableList<Repo>
}