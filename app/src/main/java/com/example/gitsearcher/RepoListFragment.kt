package com.example.gitsearcher

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gitsearcher.api.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

private const val TAG = "RepoListFragment"

class RepoListFragment : Fragment() {

    interface Callbacks{
        fun onItemSelected(item: Repo)
    }

    private lateinit var listViewModel: RepoListViewModel
    private lateinit var repoDetailFragment: RepoDetailFragment

    private lateinit var adapter: RepoAdapter
    private var callbacks: Callbacks? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRL: SwipeRefreshLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listViewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
        repoDetailFragment = RepoDetailFragment()

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repo_list, container, false)
        recyclerView = view.findViewById(R.id.repo_list_rv) as RecyclerView
        swipeRL = view.findViewById(R.id.refresh_layout) as SwipeRefreshLayout
        recyclerView.layoutManager = LinearLayoutManager(context)

        getReposCall()

        swipeRL.setOnRefreshListener {
            getReposCall()
            swipeRL.isRefreshing = false
        }

        return view
    }

    private fun getReposCall() {
        listViewModel.data.observe(viewLifecycleOwner, {
            adapter = RepoAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

}