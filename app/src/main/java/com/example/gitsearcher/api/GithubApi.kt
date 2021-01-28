package com.example.gitsearcher.api

import com.example.gitsearcher.Repo
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApi {
    @GET("/repositories")
    fun listRepos(): Call<MutableList<Repo>>
}