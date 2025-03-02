package com.elmepa.supportv2.ui.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.elmepa.designsystem.components.cards.ExpandableCard
import com.stathis.model.support.Faq

@Composable
fun FaqScreen(state: FaqView.State) {
    Scaffold(
        topBar = {

        },
        content = { paddingValues ->
            when (state) {
                is FaqView.State.Loading -> Unit
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
            .consumeWindowInsets(paddingValues)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
