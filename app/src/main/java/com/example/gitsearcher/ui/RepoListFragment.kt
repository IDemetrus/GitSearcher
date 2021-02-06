@file:Suppress("Annotator")

package com.example.gitsearcher.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gitsearcher.R
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "RepoListFragment"

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    private val viewModel: RepoListViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRL: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RepoAdapter()

        view.apply {
            progressBar = findViewById(R.id.repo_list_progress)
            recyclerView = findViewById(R.id.repo_list_rv)
            swipeRL = findViewById(R.id.refresh_layout)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }

        viewModel.repos.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }


}