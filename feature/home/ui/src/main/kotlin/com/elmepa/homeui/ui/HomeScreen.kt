package com.elmepa.homeui.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.theme.GreyBg
import com.elmepa.homedomain.model.DashboardCard
import com.elmepa.homeui.ui.components.DashboardOption
import com.elmepa.homeui.ui.components.UniversityLogoCard
import com.stathis.common.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun HomeScreen(
    state: HomeView.State,
    onAction: (HomeView.UIAction) -> Unit
) {
    Scaffold(
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
            .background(GreyBg)
            .consumeWindowInsets(paddingValues),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            UniversityLogoCard(
                modifier = Modifier.fillMaxWidth(),
                imageRes = R.drawable.elmepa_logo,
                title = stringResource(R.string.dashboard_elmepa),
                subtitle = stringResource(R.string.dashboard_det)
            )

            Spacer(modifier = Modifier.height(16.dp))
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


