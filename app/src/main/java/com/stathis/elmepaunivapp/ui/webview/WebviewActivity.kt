package com.stathis.elmepaunivapp.ui.webview

import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivityWebviewBinding
import com.stathis.elmepaunivapp.util.*

class WebviewActivity : ElmepaActivity<ActivityWebviewBinding>(R.layout.activity_webview) {

    override fun init() {}

    override fun startOps() {
        val title = intent.getStringExtra(TITLE) ?: DEFAULT_WEB_TITLE
        supportActionBar?.setupBar(title)

        val url = intent.getStringExtra(URL) ?: DEFAULT_URL
        binding.webviewWindow.apply {
            loadUrl(url)
            enableJS()
        }
    }

    override fun stopOps() {}

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}