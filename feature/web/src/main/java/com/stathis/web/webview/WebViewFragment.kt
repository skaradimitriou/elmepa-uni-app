package com.stathis.web.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.web.util.DEFAULT_URL
import com.stathis.web.util.DEFAULT_WEB_TITLE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                ElmepaAppTheme {
                    val navController = findNavController()
                    WebviewScreen(
                        onBackNavigationTap = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun WebviewScreen(onBackNavigationTap: () -> Unit) {
        var isLoading by remember { mutableStateOf(true) }

        BackHandler {
            onBackNavigationTap()
        }

        Scaffold(
            topBar = {
                val title = arguments?.getString(TITLE) ?: DEFAULT_WEB_TITLE
                TopBarWithTitleAndBackAction(
                    title = title,
                    onBackActionClick = onBackNavigationTap
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val url = arguments?.getString(URL) ?: DEFAULT_URL
                    WebView(
                        url = url,
                        onPageLoaded = { isLoading = false }
                    )

                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(MaterialTheme.spacing.xxLarge),
                            strokeWidth = 6.dp
                        )
                    }
                }
            }
        )
    }

    @Composable
    private fun WebView(url: String, onPageLoaded: () -> Unit) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = object : WebViewClient() {
                        override fun onPageCommitVisible(view: WebView?, url: String?) {
                            onPageLoaded()
                        }
                    }
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            }
        )
    }
}
