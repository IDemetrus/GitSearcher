package com.example.gitsearcher.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.gitsearcher.R
import com.example.gitsearcher.other.Constants.GITHUB_DEFAULT_URL
import com.example.gitsearcher.other.Constants.GITHUB_URI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "RepoDetailFragment"

@AndroidEntryPoint
class RepoDetailFragment : Fragment() {

    private lateinit var webView: WebView
    private var repoUrl: String? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoUrl = arguments?.getSerializable(GITHUB_URI) as String
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repo_detail, container, false)
        progressBar = view.findViewById(R.id.repo_detail_progress)
        fab = view.findViewById(R.id.repo_detail_fab)

        webView = view.findViewById(R.id.repo_detail_webview)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = ProgressBar.INVISIBLE
            }
        }
        if (repoUrl != null) {
            webView.loadUrl(repoUrl!!)
        } else {
            webView.loadUrl(GITHUB_DEFAULT_URL)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        fab.setOnClickListener{
            val sentIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                if (repoUrl != null)
                    putExtra(Intent.EXTRA_TEXT, repoUrl)
                else
                    putExtra(Intent.EXTRA_TEXT, GITHUB_DEFAULT_URL)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sentIntent, "Share repository link with..")
            startActivity(shareIntent)
        }
    }
}