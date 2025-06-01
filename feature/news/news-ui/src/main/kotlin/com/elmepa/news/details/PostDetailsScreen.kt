package com.elmepa.news.details

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAndCustomAction
import com.elmepa.designsystem.theme.Petrol
import com.elmepa.designsystem.theme.spacing
import com.elmepa.news.details.PostDetailsView.State
import com.elmepa.news.details.PostDetailsView.UIAction
import com.stathis.common.R

@Composable
internal fun PostDetailsScreen(state: State, onAction: (UIAction) -> Unit) {
    Scaffold(
        topBar = {
            PostDetailsToolbar(onAction)
        },
        content = { paddingValues ->
            when (state) {
                is State.Loading -> PostDetailsLoading(paddingValues)

                is State.Content -> {
                    PostDetailsContent(
                        paddingValues = paddingValues,
                        title = state.title,
                        image = state.image,
                        pubDate = state.pubDate,
                        htmlToLoad = state.htmlContent,
                    )
                }

                is State.Error -> Unit
            }
        }
    )
}

@Composable
private fun PostDetailsToolbar(onAction: (UIAction) -> Unit) {
    TopBarWithTitleAndBackAndCustomAction(
        title = stringResource(R.string.post_details_title),
        onBackActionClick = { onAction(UIAction.Back) },
        actions = {
            IconButton(onClick = { onAction(UIAction.Share) }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun PostDetailsLoading(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(top = MaterialTheme.spacing.small)
            .padding(horizontal = MaterialTheme.spacing.small)
            .background(MaterialTheme.colorScheme.background)
    ) {
        ShimmerEffect(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            shimmerShape = RoundedCornerShape(MaterialTheme.spacing.large)
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium))
        ShimmerEffect(
            modifier = Modifier
                .height(MaterialTheme.spacing.xLarge)
                .fillMaxWidth(),
            shimmerShape = RoundedCornerShape(MaterialTheme.spacing.large)
        )
        Spacer(Modifier.height(MaterialTheme.spacing.small))
        ShimmerEffect(
            modifier = Modifier
                .height(MaterialTheme.spacing.xLarge)
                .fillMaxWidth(),
            shimmerShape = RoundedCornerShape(MaterialTheme.spacing.large)
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium))

        repeat(15) {
            ShimmerEffect(
                modifier = Modifier
                    .height(MaterialTheme.spacing.medium)
                    .fillMaxWidth(),
                shimmerShape = RoundedCornerShape(MaterialTheme.spacing.large)
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small))
        }
    }
}

@Composable
private fun PostDetailsContent(
    paddingValues: PaddingValues,
    title: String,
    image: String,
    pubDate: String,
    htmlToLoad: String
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        item {
            AsyncImage(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                model = image,
                contentDescription = title,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Spacer(Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = pubDate,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Petrol
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
        }
        item {
            WebView(htmlToLoad)
        }
    }
}

@Composable
private fun WebView(htmlToLoad: String) {
    AndroidView(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.xSmall),
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { webView ->
            webView.loadHtmlContent(htmlToLoad)
        }
    )
}

@SuppressLint("SetJavaScriptEnabled")
private fun WebView.loadHtmlContent(htmlContent: String) {
    settings.javaScriptEnabled = true

    loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null)

    val color = when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
        Configuration.UI_MODE_NIGHT_YES -> {
            setBackgroundColor(context.getColor(R.color.dark_mode_surface))
            "#FFFFFF"
        }

        else -> {
            setBackgroundColor(context.getColor(R.color.grey_bg))
            "#000000"
        }
    }

    webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            loadUrl("javascript:document.body.style.setProperty(\"color\", \"$color\");")
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            context?.startActivity(Intent(Intent.ACTION_VIEW, request?.url.toString().toUri()))
            return true
        }
    }
}
