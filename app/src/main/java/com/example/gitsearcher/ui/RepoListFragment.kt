@file:Suppress("Annotator")

package com.example.gitsearcher.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gitsearcher.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "RepoListFragment"

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    private val viewModel: RepoListViewModel by viewModels()
    private val adapter = RepoAdapter()

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRL: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    private var searchJob: Job? = null

    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.apply {
            progressBar = findViewById(R.id.repo_list_progress)
            recyclerView = findViewById(R.id.repo_list_rv)
            swipeRL = findViewById(R.id.refresh_layout)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }
        //Load repositories
        loadRepos()

        //TODO hide progress after complete loading

        swipeRL.setOnRefreshListener {
            loadRepos()
            Log.d(TAG, "refreshed")
            swipeRL.isRefreshing = false
        }

    }

    private fun loadRepos() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.data.observe(viewLifecycleOwner, {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            })
        }
    }

}