package com.example.gitsearcher.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitsearcher.api.GitHubApi
import com.example.gitsearcher.model.Repo
import com.example.gitsearcher.other.Constants.QUERY_DEFAULT
import com.example.gitsearcher.other.Constants.QUERY_DEFAULT_PAGE
import retrofit2.HttpException
import java.io.IOException


class RepoPagingSource(
    private val gitHubApi: GitHubApi,
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: QUERY_DEFAULT_PAGE
            val response = gitHubApi.getRepos(QUERY_DEFAULT, page, params.loadSize)
            LoadResult.Page(
                data = response.items,
                prevKey = if (page > QUERY_DEFAULT_PAGE) page - 1 else null,
                nextKey = if (response.items.isNotEmpty()) page + 1 else null
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