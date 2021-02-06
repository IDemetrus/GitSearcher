package com.example.gitsearcher.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitsearcher.api.GitHubApi
import com.example.gitsearcher.model.Repo
import com.example.gitsearcher.other.Constants.DEFAULT_INIT_KEY
import retrofit2.HttpException
import java.io.IOException


class RepoPagingSource(
    private val gitHubApi: GitHubApi,
    private val query: Int
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: DEFAULT_INIT_KEY
            val response = gitHubApi.getRepos(query)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isNotEmpty()) page + 369 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition
    }
}