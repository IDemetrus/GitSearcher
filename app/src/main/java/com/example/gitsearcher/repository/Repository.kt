package com.example.gitsearcher.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitsearcher.Repo
import com.example.gitsearcher.RetrofitClient
import com.example.gitsearcher.api.GitHubApi

class Repository {

    var apiClient: GitHubApi = RetrofitClient.service

    suspend fun getRepos() = apiClient.getRepos()
}