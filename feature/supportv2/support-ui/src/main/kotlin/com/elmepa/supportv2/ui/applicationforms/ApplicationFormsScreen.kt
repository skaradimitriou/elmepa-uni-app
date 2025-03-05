package com.elmepa.supportv2.ui.applicationforms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.cards.CardWithPrompt
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.supportv2.model.ApplicationForm

@Composable
internal fun ApplicationFormsScreen(
    state: ApplicationFormsView.State,
    onClick: (ApplicationFormsView.UIAction) -> Unit
) {
    Scaffold(
        topBar = {

        },
        content = { paddingValues ->
            when (state) {
                is ApplicationFormsView.State.Loading -> ShimmerLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .consumeWindowInsets(paddingValues)
                )

                is ApplicationFormsView.State.Content -> ApplicationFormsContent(
                    paddingValues = paddingValues,
                    forms = state.forms,
                    onClick = onClick
                )

                is ApplicationFormsView.State.Error -> Unit
            }
        }
    )
}

@Composable
private fun ApplicationFormsContent(
    paddingValues: PaddingValues,
    forms: List<ApplicationForm>,
    onClick: (ApplicationFormsView.UIAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(forms, key = { it.title }) { form ->
            CardWithPrompt(
                text = form.title,
                onClick = { onClick(ApplicationFormsView.UIAction.OpenForm(form.openUrl)) }
            )
        }
    }
}

@Composable
private fun ShimmerLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = 15) {
            ShimmerEffect(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
