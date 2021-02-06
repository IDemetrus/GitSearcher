package com.example.gitsearcher.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.gitsearcher.data.RepoRepository
import com.example.gitsearcher.other.Constants.QUERY_DEFAULT_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repository: RepoRepository) : ViewModel() {

    private val currentPage = MutableLiveData(QUERY_DEFAULT_PAGE)

    val repos = currentPage.switchMap { queryString ->
        repository.getRepos(queryString).cachedIn(viewModelScope)
    }

    fun getRepos(query: Int) {
        currentPage.postValue(query)
    }
//    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int){
//        if(visibleItemCount + lastVisibleItemPosition + QUERY_DEFAULT_PAGE >= totalItemCount){
//            val immutableQuery = currentPage.value?.plus(369)
//            if (immutableQuery != null){
//                viewModelScope.launch {
//                    repository.getRepos(immutableQuery)
//                }
//            }
//        }
//    }
}
