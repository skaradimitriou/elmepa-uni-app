package com.elmepa.support.ui.applicationforms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.cards.CardWithPrompt
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAndCustomAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.support.model.ApplicationForm
import com.elmepa.support.ui.R

@Composable
internal fun ApplicationFormsScreen(
    state: ApplicationFormsView.State,
    onClick: (ApplicationFormsView.UIAction) -> Unit
) {
    var showModal by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBarWithTitleAndBackAndCustomAction(
                title = stringResource(R.string.application_forms_title),
                onBackActionClick = {
                    onClick(ApplicationFormsView.UIAction.Back)
                },
                actions = {
                    IconButton(onClick = { showModal = true }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            if (showModal) {
                AlertDialog(
                    onDismissRequest = { showModal = false },
                    title = { Text(text = stringResource(R.string.application_form_info_title)) },
                    text = { Text(text = stringResource(R.string.application_form_info_body)) },
                    confirmButton = {
                        TextButton(onClick = { showModal = false }) {
                            Text(text = stringResource(R.string.modal_btn_txt))
                        }
                    }
                )
            }

            when (state) {
                is ApplicationFormsView.State.Loading -> ShimmerLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
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
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(MaterialTheme.spacing.small)
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
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.small)
            .padding(horizontal = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
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

@Preview
@Composable
private fun ApplicationFormsScreenLoadingPreview() {
    ElmepaAppTheme {
        ApplicationFormsScreen(
            state = ApplicationFormsView.State.Loading,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun ApplicationFormsScreenPreview() {
    ElmepaAppTheme {
        ApplicationFormsScreen(
            state = ApplicationFormsView.State.Content(
                forms = listOf(
                    ApplicationForm(
                        title = "Αίτηση 1",
                        openUrl = "www.myUrl.com"
                    ),
                    ApplicationForm(
                        title = "Αίτηση 2",
                        openUrl = "www.myUrl.com"
                    ),
                    ApplicationForm(
                        title = "Αίτηση 3",
                        openUrl = "www.myUrl.com"
                    )
                ),
            ),
            onClick = {}
        )
    }
}
