package com.stathis.elmepaunivapp.ui.webview

import android.webkit.WebSettings
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaBindingActivity
import com.stathis.elmepaunivapp.databinding.ActivityWebviewBinding

class WebviewActivity : ElmepaBindingActivity<ActivityWebviewBinding>(R.layout.activity_webview) {

    override fun init() {}

    override fun startOps() {
        val url = intent.getStringExtra(resources.getString(R.string.url_tag))
        url?.let { binding.webviewWindow.loadUrl(it) }
        val webSettings: WebSettings = binding.webviewWindow.settings
        webSettings.javaScriptEnabled = true
    }

    override fun stopOps() {}

    override fun onBackPressed() {
        when {
            binding.webviewWindow.canGoBack() -> binding.webviewWindow.goBack()
            else -> super.onBackPressed()
        }
    }
}