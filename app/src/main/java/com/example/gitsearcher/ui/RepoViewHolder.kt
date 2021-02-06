package com.example.gitsearcher.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitsearcher.R
import com.example.gitsearcher.model.Repo

class RepoViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val idTv: TextView = view.findViewById(R.id.repo_list_item_id_tv)
    private val nameTv: TextView = view.findViewById(R.id.repo_list_item_name_tv)
    private val ownerTv: TextView = view.findViewById(R.id.repo_list_item_owner_tv)
    private val descTv: TextView = view.findViewById(R.id.repo_list_item_desc_tv)

    fun bind(item: Repo) {
        idTv.text = item.id
        val first = item.name[0].toUpperCase()
        nameTv.text = item.name.replaceFirst(item.name[0], first)
        ownerTv.text = item.owner?.login
        descTv.text = item.description

        //TODO change intent to start Fragment
        itemView.setOnClickListener {
            item.html_url.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                it.context.startActivity(intent)
            }
        }
    }
}