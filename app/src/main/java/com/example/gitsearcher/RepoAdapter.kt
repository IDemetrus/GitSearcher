package com.example.gitsearcher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RepoAdapter(
    private val context: Context,
    private val repoList: List<Repo>
    ) :  RecyclerView.Adapter<RepoAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val idTv : TextView = view.findViewById(R.id.repo_list_item_id_tv)
        val nameTv : TextView = view.findViewById(R.id.repo_list_item_name_tv)
        val ownerTv : TextView = view.findViewById(R.id.repo_list_item_owner_tv)
        val descTv : TextView = view.findViewById(R.id.repo_list_item_desc_tv)

        fun bind(item: Repo){
            idTv.text = item.id
            nameTv.text = item.name
            ownerTv.text = item.owner?.login
            descTv.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = repoList[position]
        val callbacks = context as RepoListFragment.Callbacks?
        holder.bind(item)
        holder.itemView.setOnClickListener { callbacks?.onItemSelected(item)}
    }

    override fun getItemCount() = repoList.size
}