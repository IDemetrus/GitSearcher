package com.example.gitsearcher.api

import androidx.lifecycle.LiveData
import com.example.gitsearcher.model.Repo
import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("repositories")val repositories: MutableList<Repo>
)













