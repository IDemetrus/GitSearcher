package com.example.gitsearcher

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

private const val TAG = "RepoDetailFragment"
private const val HTML_URI = "HTML_URI"

class RepoDetailFragment : Fragment() {

    private lateinit var webView: WebView
    private var repoUrl: String? = null
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoUrl = arguments?.getSerializable(HTML_URI) as String
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
            webView.loadUrl("https://github.com")
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share_id -> {
                val sentIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    if (repoUrl != null)
                        putExtra(Intent.EXTRA_TEXT, repoUrl)
                    else
                        putExtra(Intent.EXTRA_TEXT, "https://github.com")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sentIntent, "Share repository link with..")
                startActivity(shareIntent)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}