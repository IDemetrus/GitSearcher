package com.example.gitsearcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gitsearcher.api.GitHubApi
import com.example.gitsearcher.repository.Repository
import kotlinx.coroutines.Dispatchers

class RepoListViewModel : ViewModel() {

    val repository: Repository = Repository()

    val data = liveData(Dispatchers.IO){
        val getData = repository.getRepos()
        emit(getData)
    }
}