package com.elmepa.supportv2.ui.contact

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
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.cards.CardWithTitleAndSubtitle
import com.elmepa.supportv2.model.ContactItem
import com.elmepa.supportv2.model.ContactType
import com.elmepa.supportv2.ui.contact.components.ContactShimmerLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContactScreen(
    state: ContactView.State,
    onAction: (ContactView.UIAction) -> Unit
) {
    Scaffold(
        topBar = {
            //
        },
        content = { paddingValues ->
            when (state) {
                is ContactView.State.Loading -> ContactShimmerLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .consumeWindowInsets(paddingValues)
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
    onAction: (ContactView.UIAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
    ContactType.TELEPHONE -> {
        ContactView.UIAction.CallSecretary(telephone)
    }

    ContactType.EMAIL -> {
        ContactView.UIAction.SendEmail(email)
    }

    ContactType.WEBSITE -> {
        ContactView.UIAction.OpenUrl(descriptionLine2)
    }

    else -> null
}

