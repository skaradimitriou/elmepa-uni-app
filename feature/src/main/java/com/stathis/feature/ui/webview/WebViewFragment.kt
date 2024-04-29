package com.stathis.feature.ui.webview

import com.stathis.core.base.BaseFragment
import com.stathis.core.util.enableJS
import com.stathis.core.util.setScreenTitle
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentWebviewBinding
import com.stathis.feature.util.DEFAULT_URL
import com.stathis.feature.util.DEFAULT_WEB_TITLE
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebviewBinding>(R.layout.fragment_webview) {

    override fun init() {}

    override fun startOps() {
        val title = arguments?.getString(TITLE) ?: DEFAULT_WEB_TITLE
        setScreenTitle(title)

        val url = arguments?.getString(URL) ?: DEFAULT_URL
        binding.webView.apply {
            loadUrl(url)
            enableJS()
        }
    }

    override fun stopOps() {}
}