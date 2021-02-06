package com.example.gitsearcher.model

data class Repo(
    val id: String = "1",
    val name: String = "First Repo",
    val owner: Owner? = null,
    val description: String = "example repo for testing",
    val html_url: String = "https://github.com/idemetrus/gitsearcher"
)

class Owner(
    var login: String = ""
)
