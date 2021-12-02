package com.stathis.elmepaunivapp.ui.webview

import android.webkit.WebSettings
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : ElmepaActivity(R.layout.activity_webview) {


    override fun init() {}

    override fun startOps() {
        val url = intent.getStringExtra(resources.getString(R.string.url_tag))
        url?.let { webview_window.loadUrl(it) }
        val webSettings: WebSettings = webview_window.settings
        webSettings.javaScriptEnabled = true
    }

    override fun stopOps() {}

    override fun onBackPressed() {
        when {
            webview_window.canGoBack() -> webview_window.goBack()
            else -> super.onBackPressed()
        }
    }
}