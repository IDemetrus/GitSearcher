package com.example.gitsearcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepoAdapter(private val repoList: List<Repo>) :  RecyclerView.Adapter<RepoAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val idTv : TextView = view.findViewById(R.id.repo_list_item_id_tv)
        val nameTv : TextView = view.findViewById(R.id.repo_list_item_name_tv)
        val ownerTv : TextView = view.findViewById(R.id.repo_list_item_owner_tv)
        val descTv : TextView = view.findViewById(R.id.repo_list_item_desc_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idTv.text = repoList[position].id
        holder.nameTv.text = repoList[position].name
//        holder.ownerTv.text = repoList[position].owner
        holder.descTv.text = repoList[position].description
    }

    override fun getItemCount() = repoList.size
}