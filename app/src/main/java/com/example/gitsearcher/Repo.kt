package com.example.gitsearcher

data class Repo(
    var id: String = "1",
    var name : String = "First Repo",
    var owner: String = "Me",
    var desc: String = "example repo for testing",
    var url: String = "https://github.com/idemetrus/gitsearcher"
)
