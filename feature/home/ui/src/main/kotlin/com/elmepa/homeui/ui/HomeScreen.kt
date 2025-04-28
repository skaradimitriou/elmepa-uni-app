package com.elmepa.homeui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.topbar.TopBarWithTitle
import com.elmepa.designsystem.theme.spacing
import com.elmepa.homedomain.model.DashboardCard
import com.elmepa.homeui.ui.components.DashboardOption
import com.elmepa.homeui.ui.components.UniversityLogoCard
import com.stathis.common.R

@Composable
internal fun HomeScreen(
    state: HomeView.State,
    onAction: (HomeView.UIAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarWithTitle(stringResource(R.string.main_screen_title))
        },
        content = { paddingValues ->
            when (state) {
                is HomeView.State.Loading -> Unit
                is HomeView.State.Content -> ContentState(
                    paddingValues = paddingValues,
                    data = state.data,
                    onAction = onAction
                )
            }
        }
    )
}

@Composable
private fun ContentState(
    paddingValues: PaddingValues,
    data: List<DashboardCard>,
    onAction: (HomeView.UIAction) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background)
            .consumeWindowInsets(paddingValues),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        item(span = { GridItemSpan(2) }) {
            UniversityLogoCard(
                modifier = Modifier.fillMaxWidth(),
                imageRes = R.drawable.elmepa_logo,
                title = stringResource(R.string.dashboard_elmepa),
                subtitle = stringResource(R.string.dashboard_det)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        items(data, key = { it.seq }) { item ->
            DashboardOption(
                imageRes = item.imageRes,
                title = item.title,
                onAction = {
                    onAction(HomeView.UIAction.OptionTap(item))
                }
            )
        }

        item {
            Spacer(Modifier.height(40.dp))
        }
    }
}


