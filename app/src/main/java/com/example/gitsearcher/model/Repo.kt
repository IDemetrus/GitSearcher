package com.example.gitsearcher.model

data class Repo(
    var id: String = "1",
    var name : String = "First Repo",
    var owner: Owner? = null,
    var description: String = "example repo for testing",
    var html_url: String = "https://github.com/idemetrus/gitsearcher"
)
class Owner(
        var login: String = ""
)
