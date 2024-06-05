package com.stathis.web.webview

import com.stathis.common.base.BaseFragment
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.common.util.enableJS
import com.stathis.common.util.onPageLoaded
import com.stathis.common.util.setScreenTitle
import com.stathis.web.R
import com.stathis.web.databinding.FragmentWebviewBinding
import com.stathis.web.util.DEFAULT_URL
import com.stathis.web.util.DEFAULT_WEB_TITLE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebviewBinding>(R.layout.fragment_webview) {

    override fun init() {
        binding.isLoading = true

        val title = arguments?.getString(TITLE) ?: DEFAULT_WEB_TITLE
        setScreenTitle(title)

        val url = arguments?.getString(URL) ?: DEFAULT_URL
        binding.webView.apply {
            loadUrl(url)
            enableJS()
            onPageLoaded {
                binding.isLoading = false
            }
        }
    }

    override fun startOps() {}

    override fun stopOps() {}
}