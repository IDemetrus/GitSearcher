package com.example.gitsearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(R.layout.activity_main), RepoListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<RepoListFragment>(R.id.fragment_container)
            }
        }

    }

    override fun onItemSelected(item: Repo) {
        Toast.makeText(baseContext, "${item.name}", Toast.LENGTH_SHORT).show()
        supportFragmentManager.commit {
            replace<RepoDetailFragment>(R.id.fragment_container)
            addToBackStack(null)
        }
    }
}