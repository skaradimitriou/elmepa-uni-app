package com.stathis.common.webview

import com.stathis.common.R
import com.stathis.common.databinding.FragmentWebviewBinding
import com.stathis.common.util.DEFAULT_URL
import com.stathis.common.util.DEFAULT_WEB_TITLE
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.TITLE
import com.stathis.core.util.URL
import com.stathis.core.util.enableJS
import com.stathis.core.util.onPageLoaded
import com.stathis.core.util.setScreenTitle
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