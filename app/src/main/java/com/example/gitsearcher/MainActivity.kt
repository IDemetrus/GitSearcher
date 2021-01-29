package com.example.gitsearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

private const val TAG = "MainActivity"
private const val HTML_URI = "HTML_URI"

class MainActivity : AppCompatActivity(R.layout.activity_main), RepoListFragment.Callbacks {

    private lateinit var detailFragment: RepoDetailFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailFragment = RepoDetailFragment()

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<RepoListFragment>(R.id.fragment_container)
            }
        }

    }

    override fun onItemSelected(item: Repo) {

        val args = Bundle().apply {
            putSerializable(HTML_URI,item.html_url)
        }
        detailFragment.apply {
            arguments = args
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container,detailFragment)
            addToBackStack(null)
        }
    }
}