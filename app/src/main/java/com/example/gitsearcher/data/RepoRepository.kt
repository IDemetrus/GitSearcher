package com.example.gitsearcher.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.gitsearcher.api.GitHubApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoRepository @Inject constructor(private val gitHubApi: GitHubApi) {

    fun getRepos(query: Int) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { RepoPagingSource(gitHubApi, query) }
    ).liveData
}