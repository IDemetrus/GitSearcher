package com.example.gitsearcher.data

import com.example.gitsearcher.api.RetrofitClient
import com.example.gitsearcher.api.GitHubApi

class Repository {

    private var apiClient: GitHubApi = RetrofitClient.service

    suspend fun getRepos() = apiClient.getRepos()
}