package com.example.gitsearcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment

private const val TAG = "RepoDetailFragment"
private const val HTML_URI = "HTML_URI"

class RepoDetailFragment: Fragment() {

    private lateinit var webView: WebView
    private var repoUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoUrl = arguments?.getSerializable(HTML_URI) as String
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repo_detail,container,false)
        webView = view.findViewById(R.id.repo_detail_webview)
        if (repoUrl != null)
            webView.loadUrl(repoUrl!!)
        else
            webView.loadUrl("https://github.com")

        return view
    }
}