package com.elmepa.support.ui.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.elmepa.designsystem.components.cards.CardWithTitleAndSubtitle
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.spacing
import com.elmepa.support.model.ContactItem
import com.elmepa.support.model.ContactType
import com.elmepa.support.ui.contact.ContactView.UIAction
import com.elmepa.support.ui.contact.components.ContactShimmerLoading
import com.stathis.common.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContactScreen(
    state: ContactView.State,
    onAction: (UIAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarWithTitleAndBackAction(
                title = stringResource(R.string.contact),
                onBackActionClick = {
                    onAction(UIAction.Back)
                }
            )
        },
        content = { paddingValues ->
            when (state) {
                is ContactView.State.Loading -> ContactShimmerLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                )

                is ContactView.State.Content -> ContactContent(
                    paddingValues = paddingValues,
                    contactItems = state.contactList,
                    onAction = onAction
                )

                is ContactView.State.Error -> Unit
            }
        }
    )
}

@Composable
private fun ContactContent(
    paddingValues: PaddingValues,
    contactItems: List<ContactItem>,
    onAction: (UIAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(MaterialTheme.spacing.small)
    ) {
        items(contactItems, key = { it.contactType }) { item ->
            CardWithTitleAndSubtitle(
                title = item.title,
                subtitle = item.description,
                onAction = { item.toUiAction()?.let { onAction(it) } }
            )
        }
    }
}

private fun ContactItem.toUiAction() = when (contactType) {
    ContactType.TELEPHONE -> UIAction.CallSecretary(telephone)
    ContactType.EMAIL -> UIAction.SendEmail(email)
    ContactType.WEBSITE -> UIAction.OpenUrl(descriptionLine2)
    else -> null
}

