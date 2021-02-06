package com.example.gitsearcher.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gitsearcher.data.RepoRepository
import com.example.gitsearcher.model.Repo
import com.example.gitsearcher.other.Constants.QUERY_DEFAULT
import com.example.gitsearcher.other.Constants.QUERY_DEFAULT_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repository: RepoRepository) : ViewModel() {

    val data = repository.getRepos().cachedIn(viewModelScope)
}
