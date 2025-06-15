package com.elmepa.students.ui.acadschedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.cards.CardWithTitleAndSubtitle
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.spacing
import com.elmepa.students.ui.acadschedule.AcademicScheduleView.State
import com.elmepa.students.ui.acadschedule.AcademicScheduleView.UIAction
import com.stathis.common.R
import com.stathis.model.UiModel
import com.stathis.model.students.AcademicScheduleEntry
import com.stathis.model.students.AcademicScheduleTitle

@Composable
internal fun AcademicScheduleScreen(state: State, onAction: (UIAction) -> Unit) {
    Scaffold(
        topBar = {
            TopBarWithTitleAndBackAction(
                title = stringResource(R.string.acad_schedule_title),
                onBackActionClick = { onAction(UIAction.OnBackArrowTap) }
            )
        },
        content = { paddingValues ->
            when (state) {
                is State.Loading -> AcademicScheduleLoading(
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                )

                is State.Content -> AcademicScheduleContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                        .background(MaterialTheme.colorScheme.background),
                    data = state.items
                )

                is State.Error -> Unit
            }
        }
    )
}

@Composable
private fun AcademicScheduleLoading(modifier: Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(MaterialTheme.spacing.small)
    ) {
        items(count = 10) {
            ShimmerEffect(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AcademicScheduleContent(
    modifier: Modifier,
    data: List<UiModel>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(MaterialTheme.spacing.small)
    ) {
        items(data) { item ->
            when (item) {
                is AcademicScheduleTitle -> {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.spacing.medium),
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }

                is AcademicScheduleEntry -> {
                    CardWithTitleAndSubtitle(
                        title = item.title,
                        subtitle = item.date,
                        onAction = {}
                    )
                }
            }
        }
    }
}
