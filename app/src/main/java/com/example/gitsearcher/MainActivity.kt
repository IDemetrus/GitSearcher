package com.example.gitsearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var repoListFragment: RepoListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repoListFragment = RepoListFragment()

        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, repoListFragment, "repoListFragment")
                    .commit()
        }
    }
}