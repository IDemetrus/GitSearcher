package com.example.gitsearcher.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.gitsearcher.api.GitHubApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoRepository @Inject constructor(private val gitHubApi: GitHubApi) {

    fun getRepos() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = { RepoPagingSource(gitHubApi) }
    ).liveData
}