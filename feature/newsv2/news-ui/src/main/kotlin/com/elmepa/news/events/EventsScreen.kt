package com.elmepa.news.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.news.announcements.components.AnnouncementCard
import com.elmepa.news.events.EventsView.UIAction
import com.stathis.common.R
import com.stathis.model.announcements.Event

@Composable
internal fun EventsScreen(
    events: LazyPagingItems<Event>,
    onAction: (UIAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarWithTitleAndBackAction(
                title = stringResource(R.string.events),
                onBackActionClick = { onAction(UIAction.Back) }
            )
        },
        content = { paddingValues ->
            EventsContent(paddingValues, events, onAction)
        }
    )
}

@Composable
private fun EventsContent(
    paddingValues: PaddingValues,
    events: LazyPagingItems<Event>,
    onAction: (UIAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        item {
            when (events.loadState.append) {
                is LoadState.Loading -> ShimmerLoading()
                is LoadState.Error -> Unit
                else -> Unit
            }
        }

        items(events.itemCount) { index ->
            events[index]?.let { event ->
                AnnouncementCard(
                    imageUrl = event.imageResource,
                    datePublished = event.pubDate,
                    title = event.name,
                    subtitle = event.description,
                    onClick = {
                        onAction(UIAction.OnEventTap(event))
                    }
                )
            }
        }
    }
}

@Composable
private fun ShimmerLoading() {
    repeat(10) {
        ShimmerEffect(
            modifier = Modifier
                .height(200.dp)
                .clip(RoundedCornerShape(8))
                .fillMaxWidth()
        )

        Spacer(Modifier.height(MaterialTheme.spacing.small))
    }
}

@PreviewLightDark
@Composable
private fun EventsScreenPreview() {
    ElmepaAppTheme {

    }
}
