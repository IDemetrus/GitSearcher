package com.example.gitsearcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gitsearcher.data.Repository
import kotlinx.coroutines.Dispatchers

class RepoListViewModel : ViewModel() {

    private val repository: Repository = Repository()

    val data = liveData(Dispatchers.IO) {
        val getData = repository.getRepos()
        emit(getData)
    }
}