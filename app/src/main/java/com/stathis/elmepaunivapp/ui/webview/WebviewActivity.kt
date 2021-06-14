package com.stathis.elmepaunivapp.ui.webview

import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : ElmepaActivity(R.layout.activity_webview) {


    override fun init() {}

    override fun startOps() {
        val url = intent.getStringExtra("URL")
        webview_window.loadUrl(url)
    }

    override fun stopOps() {}
}