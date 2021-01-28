package com.example.gitsearcher

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitsearcher.api.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RepoListFragment"

class RepoListFragment : Fragment() {

    private lateinit var repoList: Call<MutableList<Repo>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val apiUrl = "https://api.github.com"
        val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val gitService = retrofit.create(GitHubApi::class.java)
        repoList = gitService.listRepos()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repo_list, container, false)
        val recyclerView = view.findViewById(R.id.repo_list_rv) as RecyclerView

        repoList.enqueue(object : Callback<MutableList<Repo>> {
            override fun onResponse(call: Call<MutableList<Repo>>, response: Response<MutableList<Repo>>) {
                Log.d(TAG, "${response.body()}")
                val adapter = RepoAdapter(response.body() as List<Repo>)
                adapter.notifyDataSetChanged()
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MutableList<Repo>>, t: Throwable) {
                Log.d(TAG, "$t")
            }
        })


        return view
    }

    //TODO fix getRepos method

}