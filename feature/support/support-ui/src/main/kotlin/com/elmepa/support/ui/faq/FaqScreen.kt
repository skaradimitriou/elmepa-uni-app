package com.elmepa.support.ui.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.text.HtmlCompat
import com.elmepa.designsystem.components.cards.ExpandableCard
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.spacing
import com.elmepa.support.model.Faq
import com.elmepa.support.ui.faq.FaqView.UIAction
import com.elmepa.support.ui.faq.components.FaqShimmerLoading
import com.stathis.common.R

@Composable
fun FaqScreen(state: FaqView.State, onAction: (UIAction) -> Unit) {
    Scaffold(
        topBar = {
            TopBarWithTitleAndBackAction(
                title = stringResource(R.string.faq_title),
                onBackActionClick = {
                    onAction(UIAction.Back)
                }
            )
        },
        content = { paddingValues ->
            when (state) {
                is FaqView.State.Loading -> FaqShimmerLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                )

                is FaqView.State.Content -> FaqContent(
                    paddingValues = paddingValues,
                    faqs = state.faqs
                )

                is FaqView.State.Error -> Unit
            }
        }
    )
}

@Composable
private fun FaqContent(
    paddingValues: PaddingValues,
    faqs: List<Faq>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(MaterialTheme.spacing.small)
    ) {
        items(faqs, key = { it.seq }) { faq ->
            val text = HtmlCompat.fromHtml(faq.answer, HtmlCompat.FROM_HTML_MODE_LEGACY)

            ExpandableCard(
                text = "${faq.seq}. ${faq.question}",
                description = text.toString()
            )
        }
    }
}
